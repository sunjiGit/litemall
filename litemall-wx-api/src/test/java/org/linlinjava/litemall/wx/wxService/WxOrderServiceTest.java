package org.linlinjava.litemall.wx.wxService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.litemall.wx.service.WxOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WxOrderServiceTest {

    @Autowired
    private WxOrderService orderService;

    @Test
    public void testSubmitOrder() {

    }

    @Test
    public void testListOrderAndDetailOrder() {

    }

    @Test
    public void testCancelOrder() {

    }

}
