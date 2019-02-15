--数据库初始化脚本

--创建数据库
CREATE DATABASE seckill;
--使用数据库
use seckill;
--创建表
CREATE TABLE seckill(
 `seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
 `name` varchar(120) NOT NULL COMMENT '商品名称',
 `number` int NOT NULL COMMENT '库存数量',
 `start_time` varchar(20) NOT NULL COMMENT '秒杀开启时间',
 `end_time` varchar(20) NOT NULL COMMENT '秒杀结束时间',
 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 PRIMARY KEY(seckill_id),
 key idx_start_time(start_time),
 key idx_end_time(end_time),
 key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表'

--初始化数据
insert into
  seckill(name, number, start_time, end_time)
values
  ('iphone6', 100, '1547078400', '1547078400'),
  ('ipad2', 200, '1547078400', '1547078400'),
  ('ps3', 300, '1547078400', '1547078400'),
  ('ps2', 400, '1547078400', '1547078400');

--秒杀记录表
--用户登录认证和相关的信息
create table success_killed(
  `seckill_id` bigint NOT NULL COMMENT '商品ID',
  `user_phone` bigint NOT NULL COMMENT '用户手机号',
  `state` tinyint NOT NULL DEFAULT -1 COMMENT '状态标识: -1无效 0成功 1已付款 2已发货',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY(seckill_id, user_phone), /*复合主键*/
  key idx_create_time(create_time)
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

