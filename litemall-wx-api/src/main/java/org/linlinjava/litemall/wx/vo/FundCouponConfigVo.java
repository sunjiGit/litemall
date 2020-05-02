package org.linlinjava.litemall.wx.vo;

import java.util.List;
import java.util.Objects;

public class FundCouponConfigVo {
    private Integer id;
    private Integer amount;
    private String desc;
    private String totalCouponNumber;
    private String totalCouponAmount;

    private List<FundCouponVo> couponVos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTotalCouponNumber() {
        return totalCouponNumber;
    }

    public void setTotalCouponNumber(String totalCouponNumber) {
        this.totalCouponNumber = totalCouponNumber;
    }

    public String getTotalCouponAmount() {
        return totalCouponAmount;
    }

    public void setTotalCouponAmount(String totalCouponAmount) {
        this.totalCouponAmount = totalCouponAmount;
    }

    public List<FundCouponVo> getCouponVos() {
        return couponVos;
    }

    public void setCouponVos(List<FundCouponVo> couponVos) {
        this.couponVos = couponVos;
    }

    @Override
    public String toString() {
        return "FundCouponConfigVo{" +
                "id=" + id +
                ", amount=" + amount +
                ", desc='" + desc + '\'' +
                ", totalCouponNumber='" + totalCouponNumber + '\'' +
                ", totalCouponAmount='" + totalCouponAmount + '\'' +
                ", couponVos=" + couponVos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FundCouponConfigVo that = (FundCouponConfigVo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(desc, that.desc) &&
                Objects.equals(totalCouponNumber, that.totalCouponNumber) &&
                Objects.equals(totalCouponAmount, that.totalCouponAmount) &&
                Objects.equals(couponVos, that.couponVos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, desc, totalCouponNumber, totalCouponAmount, couponVos);
    }
}
