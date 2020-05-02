package org.linlinjava.litemall.wx.vo;

import java.util.Objects;

public class FundCouponVo {
    private Integer couponId;
    private Integer couponAmount;
    private String couponName;
    private Integer couponSize;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Integer couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getCouponSize() {
        return couponSize;
    }

    public void setCouponSize(Integer couponSize) {
        this.couponSize = couponSize;
    }

    @Override
    public String toString() {
        return "FundCouponVo{" +
                "couponId=" + couponId +
                ", couponAmount=" + couponAmount +
                ", couponName='" + couponName + '\'' +
                ", couponSize=" + couponSize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FundCouponVo couponVo = (FundCouponVo) o;
        return Objects.equals(couponId, couponVo.couponId) &&
                Objects.equals(couponAmount, couponVo.couponAmount) &&
                Objects.equals(couponName, couponVo.couponName) &&
                Objects.equals(couponSize, couponVo.couponSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(couponId, couponAmount, couponName, couponSize);
    }
}
