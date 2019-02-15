package org.seckill.dto;

/**
 * Created by YH on 2019/1/11.
 */
public class Exposer {
    private boolean open;
    private String md5;
    private long seckillId;
    private String now;
    private String start;
    private String end;

    public Exposer(boolean open, String md5, long seckillId) {
        this.open = open;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean open, long seckillId, String now, String end, String start) {
        this.open = open;
        this.seckillId = seckillId;
        this.now = now;
        this.end = end;
        this.start = start;
    }

    public Exposer(long seckillId, boolean open) {
        this.seckillId = seckillId;
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "open=" + open +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now='" + now + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
