package org.linlinjava.litemall.admin.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.litemall.admin.web.AdminStoreController;
import org.linlinjava.litemall.core.store.enums.StoreType;
import org.linlinjava.litemall.db.domain.LitemallStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StoreControllerTest {

    private Logger logger = LoggerFactory.getLogger(StoreControllerTest.class);

    @Resource
    private AdminStoreController adminStoreController;

    @Test
    public void testCreate() {
        LitemallStore store = new LitemallStore();
        store.setName("上海浦东第一店-测试");
        store.setRegionId(1097);
        store.setAddressDetail("上海市浦东新区顺和路126弄25号");
        store.setOrderStartSeq(1000);
        store.setType(StoreType.DINE_IN.getCode());
        store.setWeekdayStart("1000");
        store.setWeekdayEnd("2130");
        store.setWeekendStart("1000");
        store.setWeekendEnd("1730");
        Object result = adminStoreController.create(store);

        logger.info(String.format("param=%s, result=%s", store, result));
    }

    @Test
    public void testUpdate() {
        LitemallStore store = new LitemallStore();
        store.setId(1);
        store.setWeekendEnd("1800");

        Object result = adminStoreController.update(store);
        logger.info(String.format("param=%s, result=%s", store, result));
    }
}
