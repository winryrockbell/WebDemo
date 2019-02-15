package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.springframework.test.annotation.Repeat;

import java.util.List;

/**
 * Created by YH on 2019/1/11.
 */
public interface SeckillService {
    List<Seckill> getSeckillList();

    Seckill getById(long seckillId);

    Exposer exportSeckillUrl(long seckillId);

    SeckillResult executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;
    Boolean updateTime(long seckillId, String startTime, String endTime);
}
