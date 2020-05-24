package org.linlinjava.litemall.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.litemall.core.printer.jiabo.JiaboSendMsg;
import org.linlinjava.litemall.db.domain.LitemallOrder;
import org.linlinjava.litemall.db.domain.LitemallOrderGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CloudPrinterTest {

    @Autowired
    private JiaboSendMsg jiaboSendMsg;

    @Test
    public void test() throws InterruptedException {
        String printCode = "00391282551212082";

        LitemallOrder order = new LitemallOrder();
        order.setId(1);
        order.setStoreId(2);
        order.setOrderSn("001");
        order.setConsignee("孙先生");
        order.setMobile("15900986156");
        order.setAddress("嘉定区红石路730弄汇丰凯苑25号楼103");

        order.setDiscountPrice(new BigDecimal(10));
        order.setCouponPrice(new BigDecimal(15));
        order.setActualPrice(new BigDecimal(45));

        List<LitemallOrderGoods> orderGoods = new ArrayList<>();
        LitemallOrderGoods og = new LitemallOrderGoods();
        og.setGoodsName("暴雨梨花汁");
        og.setNumber(Short.parseShort("1"));
        og.setPrice(new BigDecimal(20));
        orderGoods.add(og);
        og = new LitemallOrderGoods();
        og.setGoodsName("雨前玫瑰茶");
        og.setNumber(Short.parseShort("2"));
        og.setPrice(new BigDecimal(50));
        orderGoods.add(og);

        jiaboSendMsg.printOrder(printCode, order, orderGoods);

        TimeUnit.SECONDS.sleep(2);

        jiaboSendMsg.printOrderPrepare(printCode, order, orderGoods);
    }

}
