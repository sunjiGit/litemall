package org.linlinjava.litemall.wx.vo.params;


import java.util.Objects;

public class OrderProductsVo {
    private Integer productId;
    private Integer productNum;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductsVo that = (OrderProductsVo) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(productNum, that.productNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productNum);
    }

    @Override
    public String toString() {
        return "OrderProductsVo{" +
                "productId=" + productId +
                ", productNum=" + productNum +
                '}';
    }
}
