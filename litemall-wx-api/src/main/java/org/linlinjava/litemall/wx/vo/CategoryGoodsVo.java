package org.linlinjava.litemall.wx.vo;

import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.db.domain.LitemallCategory;
import org.linlinjava.litemall.db.domain.LitemallGoods;

import java.util.List;
import java.util.Objects;

public class CategoryGoodsVo {

    private LitemallCategory category;
    private List<GoodAndProductVo> goodProductList;

    public LitemallCategory getCategory() {
        return category;
    }

    public void setCategory(LitemallCategory category) {
        this.category = category;
    }

    public List<GoodAndProductVo> getGoodProductList() {
        return goodProductList;
    }

    public void setGoodProductList(List<GoodAndProductVo> goodProductList) {
        this.goodProductList = goodProductList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryGoodsVo that = (CategoryGoodsVo) o;
        return Objects.equals(category, that.category) &&
                Objects.equals(goodProductList, that.goodProductList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, goodProductList);
    }

    @Override
    public String toString() {
        return "CategoryGoodsVo{" +
                "category=" + category +
                ", goodProductList=" + JacksonUtil.toJson(goodProductList) +
                '}';
    }
}
