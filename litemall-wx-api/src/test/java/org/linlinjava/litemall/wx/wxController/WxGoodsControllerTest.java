package org.linlinjava.litemall.wx.wxController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallCategory;
import org.linlinjava.litemall.db.domain.LitemallGoods;
import org.linlinjava.litemall.db.service.LitemallCategoryService;
import org.linlinjava.litemall.db.service.LitemallGoodsService;
import org.linlinjava.litemall.wx.service.WxStoreService;
import org.linlinjava.litemall.wx.web.WxGoodsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WxGoodsControllerTest {

    private final Log logger = LogFactory.getLog(WxGoodsControllerTest.class);

    @Autowired
    private LitemallCategoryService categoryService;
    @Autowired
    private LitemallGoodsService goodsService;
    @Autowired
    private WxGoodsController wxGoodsController;


    @Test
    public void listL1CategoryAndGoods() {
        Integer uid = 2;

        Object result = wxGoodsController.listL1CategoryAndGoods(uid, "add_time", "desc");

        System.out.println("result:\n" + result);
    }
//    {errno=0, data={total=3, pages=1, limit=3, page=1, list=[CategoryGoodsVo{
//        category=LitemallCategory [Hash = 392476084, IS_DELETED=false, NOT_DELETED=false, id=1011000, name=婴童, keywords=, desc=爱，从心开始, pid=0, iconUrl=..., picUrl=..., level=L1, sortOrder=7, addTime=2018-02-01T00:00, updateTime=2018-02-01T00:00, deleted=false],
//        goodsList=[
//        {"id":1033000,"goodsSn":"1033000","name":"新生彩棉初衣礼盒（婴童）","categoryId":1020003,"brandId":0,"gallery":["..."],"keywords":"","brief":"来自天然彩棉的礼物","isOnSale":true,"sortOrder":2,"picUrl":"...","shareUrl":"","isNew":false,"isHot":false,"unit":"件","counterPrice":219.00,"retailPrice":199.00,"addTime":{"hour":0,"minute":0,"second":0,"nano":0,"dayOfYear":32,"dayOfWeek":"THURSDAY","month":"FEBRUARY","dayOfMonth":1,"year":2018,"monthValue":2,"chronology":{"id":"ISO","calendarType":"iso8601"}},"updateTime":{"hour":0,"minute":0,"second":0,"nano":0,"dayOfYear":32,"dayOfWeek":"THURSDAY","month":"FEBRUARY","dayOfMonth":1,"year":2018,"monthValue":2,"chronology":{"id":"ISO","calendarType":"iso8601"}},"deleted":false,"detail":null},
//        {"id":1116004,"goodsSn":"1116004","name":"条纹长袖海魂衫（男婴童）",

    @Test
    public void listByFirstCategoryTest() {
        Integer firstCategoryId = 1013001;
//        Integer firstCategoryId = -1013001;
        Integer page = 1;
        Integer limit = 20;

        // 查询商品所属类目列表。
        List<LitemallCategory> categoryList = categoryService.queryByPid(firstCategoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            logger.info(String.format("no categoryList for firstCateId=%s", firstCategoryId));
            logger.info("ResponseUtil.ok()" + ResponseUtil.ok());
            return;
        }

        //查询列表数据
        List<Integer> cateIds = categoryList.stream().map(LitemallCategory::getId).collect(Collectors.toList());
        List<LitemallGoods> goodsList = goodsService.queryByCategory(cateIds, page, limit);

        logger.info("ResponseUtil.okList(list)" + ResponseUtil.okList(goodsList));
    }

}
