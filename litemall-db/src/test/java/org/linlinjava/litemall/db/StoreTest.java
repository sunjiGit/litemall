package org.linlinjava.litemall.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.litemall.db.domain.LitemallStore;
import org.linlinjava.litemall.db.service.LitemallStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StoreTest {
    @Autowired
    private LitemallStoreService storeService;

    @Test
    public void testStoreOperator() {
        List<LitemallStore> list = storeService.all();
        System.out.println("list=" + list);

        LitemallStore store = new LitemallStore();
        store.setAddressDetail("北京市朝阳区阜通东大街6号");
        store.setName("测试北京门店");
        store.setLongitude("116.481028");
        store.setLatitude("39.989643");
        store.setAddTime(LocalDateTime.now());
        store.setUpdateTime(LocalDateTime.now());
        storeService.add(store);

        list = storeService.all();
        System.out.println("list after add=" + list);

        if (!CollectionUtils.isEmpty(list)) {
            LitemallStore update = list.get(0);
            update.setName(update.getName()+"修");
            update.setUpdateTime(LocalDateTime.now());
            int i = storeService.updateById(store);
            System.out.println("udpate i=" + i);

            list = storeService.all();
            System.out.println("list after update=" + list);

            storeService.deleteById(update.getId());

            list = storeService.all();
            System.out.println("list after delete=" + list);
        }
    }
}
