package org.linlinjava.litemall.admin.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.litemall.admin.web.AdminStoreController;
import org.linlinjava.litemall.admin.web.AdminStoreInventoryController;
import org.linlinjava.litemall.core.store.enums.StoreInventoryOperateType;
import org.linlinjava.litemall.core.store.enums.StoreType;
import org.linlinjava.litemall.db.domain.LitemallStore;
import org.linlinjava.litemall.db.domain.LitemallStoreInventoryFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StoreInventoryControllerTest {

    private Logger logger = LoggerFactory.getLogger(StoreInventoryControllerTest.class);

    @Resource
    private AdminStoreInventoryController storeInventoryController;

    @Test
    public void testAdd() {
        LitemallStoreInventoryFlow flow = new LitemallStoreInventoryFlow();
        flow.setStoreId(1);
        flow.setGoodsId(1);
        flow.setAmount(5);
        flow.setOperateType(StoreInventoryOperateType.MAKE_IN.getCode());
        flow.setOperator("sunji-test");
        storeInventoryController.add(flow);

        Object result = storeInventoryController.detail(1);
        logger.info(String.format("param=%s, result=%s", flow, result));
    }

    @Test
    public void testAdjust() {
        LitemallStoreInventoryFlow flow = new LitemallStoreInventoryFlow();
        flow.setStoreId(1);
        flow.setGoodsId(2);
        flow.setAmount(10);
        flow.setOperateType(StoreInventoryOperateType.ADJUST_IN.getCode());
        flow.setOperator("sunji-test");
        storeInventoryController.adjust(flow);

        Object result = storeInventoryController.detail(1);
        logger.info(String.format("1 param=%s, result=%s", flow, result));

        flow = new LitemallStoreInventoryFlow();
        flow.setStoreId(1);
        flow.setGoodsId(2);
        flow.setAmount(-5);
        flow.setOperateType(StoreInventoryOperateType.ADJUST_OUT.getCode());
        flow.setOperator("sunji-test");
        storeInventoryController.adjust(flow);

        result = storeInventoryController.detail(1);
        logger.info(String.format("2 param=%s, result=%s", flow, result));
    }
}
