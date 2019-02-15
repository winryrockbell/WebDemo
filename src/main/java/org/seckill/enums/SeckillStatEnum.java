package org.seckill.enums;

/**
 * Created by YH on 2019/1/11.
 */
public enum SeckillStatEnum {
    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀结束"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统错误"),
    DATA_REWRITE(-3, "md5异常");

    private int state;

    private String stateInfo;

    SeckillStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStatEnum stateof(int index)
    {
        for(SeckillStatEnum state : values())
        {
            if(state.getState() == index)
            {
                return state;
            }
        }
        return null;
    }

}
