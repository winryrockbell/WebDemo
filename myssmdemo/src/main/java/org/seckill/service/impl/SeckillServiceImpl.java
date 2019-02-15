package org.seckill.service.impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * Created by YH on 2019/1/11.
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //注入Service依赖
    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;
    //生成md5专用
    private final String slat = "as1%12;^&";
    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if(seckill == null)
        {
            return new Exposer(seckillId, false);
        }
        String startTime = seckill.getStartTime();
        String endTime = seckill.getEndTime();
        long nowTime = System.currentTimeMillis() / 1000;
        if(nowTime < Long.parseLong(startTime) || nowTime > Long.parseLong(endTime)){
            return new Exposer(false, seckillId, String.valueOf(nowTime), endTime, startTime);
        }
        String md5 = getMD5(seckillId); //TODO
        return new Exposer(true, md5, seckillId);
    }

    @Override
    @Transactional
    /*
    *  使用注解控制事务方法的优点:
    *  1.便于约定，明确标注哪个方法被事务控制了起来
    *  也便于了解这个方法不要穿插太多的网络操作
    *  2.不是所有的方法都需要事务，普通只读，只改一条的时候都不用事务控制
    *
    */
    public SeckillResult executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException {
        if(md5 == null || !md5.equals(getMD5(seckillId))){
            throw new SeckillException("md5 check fail");
        }
        //执行逻辑
        String nowTime = System.currentTimeMillis() / 1000 + "";
        try {
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if(updateCount <= 0){
                throw new SeckillCloseException("seckill is closed");
            } else {
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone, nowTime);
                if (insertCount <= 0){
                    throw new RepeatKillException("repeated seckill");
                }else{
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillResult(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        }
        catch(SeckillCloseException e){
            throw e;
        }
        catch(RepeatKillException e){
            throw e;
        }
        catch (Exception e){
            logger.error(e.getMessage(), e);
            throw new SeckillException("Other Exception" + e.getMessage());
        }
    }

    private String getMD5(long seckillId){
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    public Boolean updateTime(long seckillId, String startTime, String endTime) {
        int result = seckillDao.updateTime(startTime, endTime, seckillId);
        if(result <= 0){
            return false;
        }else{
            return true;
        }
    }
}
