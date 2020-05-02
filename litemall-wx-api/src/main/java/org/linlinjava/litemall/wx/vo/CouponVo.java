package org.linlinjava.litemall.wx.vo;

import java.time.LocalDateTime;
import java.util.Objects;

public class CouponVo {
    private Integer id;
    private Integer cid;
    private String name;
    private String desc;
    private String tag;
    private String min;
    private String discount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean available;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouponVo couponVo = (CouponVo) o;
        return available == couponVo.available &&
                Objects.equals(id, couponVo.id) &&
                Objects.equals(cid, couponVo.cid) &&
                Objects.equals(name, couponVo.name) &&
                Objects.equals(desc, couponVo.desc) &&
                Objects.equals(tag, couponVo.tag) &&
                Objects.equals(min, couponVo.min) &&
                Objects.equals(discount, couponVo.discount) &&
                Objects.equals(startTime, couponVo.startTime) &&
                Objects.equals(endTime, couponVo.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cid, name, desc, tag, min, discount, startTime, endTime, available);
    }

    @Override
    public String toString() {
        return "CouponVo{" +
                "id=" + id +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", tag='" + tag + '\'' +
                ", min='" + min + '\'' +
                ", discount='" + discount + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", available=" + available +
                '}';
    }
}
