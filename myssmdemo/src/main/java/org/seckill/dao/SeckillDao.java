package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;
import java.util.List;

/**
 * Created by YH on 2019/1/9.
 */
public interface SeckillDao {

    int reduceNumber(@Param("seckillId")long seckillId, @Param("reduceTime")String reduceTime);

    //查询指定商品
    Seckill queryById(long seckillId);

    //分页查询列表，这里如果是多个参数的时候，要添加注解，不然
    //mybatis找不到实际的参数
    List<Seckill> queryAll(@Param("offset")int offset, @Param("limit")int limit);

    int updateTime(@Param("startTime")String startTime, @Param("endTime")String endTime, @Param("seckillId")long seckillId);
}
