package org.seckill.dto;

/**
 * Created by YH on 2019/1/14.
 */
//封装Controller的结果，以便统一返回给前端,主要封装ajax返回的json数据
public class SeckillJson<T> {
    private boolean success;

    private T data;

    private String error;

    public SeckillJson(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public SeckillJson(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

