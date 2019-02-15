package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * Created by YH on 2019/1/9.
 */
public interface SuccessKilledDao {
    //插入购买记录
    int insertSuccessKilled(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone, @Param("createTime") String createTime);
    //
    SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone);
}
