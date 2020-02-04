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
