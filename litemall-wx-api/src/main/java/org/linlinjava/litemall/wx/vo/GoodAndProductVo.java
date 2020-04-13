package org.linlinjava.litemall.wx.vo;

import org.linlinjava.litemall.db.domain.LitemallGoods;
import org.linlinjava.litemall.db.domain.LitemallGoodsProduct;

import java.util.Objects;

public class GoodAndProductVo {

    private LitemallGoods goods;
    private LitemallGoodsProduct product;


    public LitemallGoods getGoods() {
        return goods;
    }

    public void setGoods(LitemallGoods goods) {
        this.goods = goods;
    }

    public LitemallGoodsProduct getProduct() {
        return product;
    }

    public void setProduct(LitemallGoodsProduct product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodAndProductVo that = (GoodAndProductVo) o;
        return Objects.equals(goods, that.goods) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goods, product);
    }

    @Override
    public String toString() {
        return "GoodAndProductVo{" +
                "goods=" + goods +
                ", product=" + product +
                '}';
    }
}
