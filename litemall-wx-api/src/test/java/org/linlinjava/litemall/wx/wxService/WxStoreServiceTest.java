package org.linlinjava.litemall.wx.wxService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.litemall.wx.service.WxStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WxStoreServiceTest {
    @Autowired
    private WxStoreService wxStoreService;

    @Test
    public void testAllStoresAndDistance() {
        String body = "{\"lon\":\"115.483038\",\"lat\":\"39.790633\"}";
        Object res = wxStoreService.allStoresAndDistance(body);
        System.out.println(res);
    }

    @Test
    public void testNearestStoresAndDistance() {
        String body = "{\"lon\":\"115.483038\",\"lat\":\"39.790633\"}";
        Object res = wxStoreService.nearestStoreAndDistance(body);
        System.out.println(res);
    }

    @Test
    public void testAddressToStore() {
        String body = "{\"address\":\"北京市朝阳区阜通东大街600号\"}";
        Object res = wxStoreService.addressToStore(body);
        System.out.println(res);
    }

}
