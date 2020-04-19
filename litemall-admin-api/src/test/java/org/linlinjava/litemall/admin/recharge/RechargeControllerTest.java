package org.linlinjava.litemall.admin.recharge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.litemall.admin.vo.RechargeConfigVo;
import org.linlinjava.litemall.admin.vo.RechargeCouponVo;
import org.linlinjava.litemall.admin.web.AdminCouponController;
import org.linlinjava.litemall.admin.web.AdminRechargeController;
import org.linlinjava.litemall.admin.web.AdminStoreController;
import org.linlinjava.litemall.db.domain.LitemallCoupon;
import org.linlinjava.litemall.db.domain.LitemallStore;
import org.linlinjava.litemall.db.enums.store.StoreType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RechargeControllerTest {

    private Logger logger = LoggerFactory.getLogger(RechargeControllerTest.class);

    @Resource
    private AdminRechargeController rechargeController;
    @Resource
    private AdminCouponController couponController;

    @Test
    public void testCreate() {
//        RechargeConfigVo vo = new RechargeConfigVo();
//
//        Integer amount = 200_00;
//        String desc = "充200返23";
//
//        // id=9 3元代金券
//        // id=10 5元代金券
//        Integer couponId = 9;
//        LitemallCoupon coupon = (LitemallCoupon) ((Map<String, Object>)couponController.read(couponId)).get("data");
//
//        List<RechargeCouponVo> couponVos = new ArrayList<>();
//        RechargeCouponVo couponVo = createNewRechargeCouponVo(coupon, 1);
//        couponVos.add(couponVo);
//
//        couponId = 10;
//        coupon = (LitemallCoupon) ((Map<String, Object>)couponController.read(couponId)).get("data");
//        couponVo = createNewRechargeCouponVo(coupon, 4);
//        couponVos.add(couponVo);
//
//        vo.setAmount(amount);
//        vo.setDesc(desc);
//        vo.setCouponVos(couponVos);
//
//        Object res = rechargeController.create(vo);
//        System.out.println(res);

        RechargeConfigVo vo = new RechargeConfigVo();

        Integer amount = 50_00;
        String desc = "充50返3";

        // id=9 3元代金券
        // id=10 5元代金券
        Integer couponId = 9;
        LitemallCoupon coupon = (LitemallCoupon) ((Map<String, Object>)couponController.read(couponId)).get("data");

        List<RechargeCouponVo> couponVos = new ArrayList<>();
        RechargeCouponVo couponVo = createNewRechargeCouponVo(coupon, 1);
        couponVos.add(couponVo);

        vo.setAmount(amount);
        vo.setDesc(desc);
        vo.setCouponVos(couponVos);

        Object res = rechargeController.create(vo);
        System.out.println(res);
    }

    private RechargeCouponVo createNewRechargeCouponVo(LitemallCoupon coupon, int number) {
        RechargeCouponVo vo = new RechargeCouponVo();

        vo.setCouponId(coupon.getId());
        vo.setCouponName(coupon.getName());
        vo.setCouponAmount(coupon.getDiscount().multiply(new BigDecimal(100)).intValue());
        vo.setCouponSize(number);

        return vo;
    }

    @Test
    public void testUpdate() {
        Integer id = 1;
        Object res = rechargeController.read(id);
        System.out.println(res);

        RechargeConfigVo vo = (RechargeConfigVo) ((HashMap<String, Object>) res).get("data");
        vo.setDesc("充100返13");
        vo.getCouponVos().get(1).setCouponSize(1);

        Object resU = rechargeController.update(vo);
        System.out.println(resU);
    }

    @Test
    public void testDelete() {
        Integer deleteId = 4;
        Object res = rechargeController.delete(deleteId);
        System.out.println(res);

        Object result = rechargeController.list(1, 10, "add_time", "asc");
        System.out.println(result);
    }

    @Test
    public void testList() {
        Object result = rechargeController.list(1, 10, "add_time", "asc");
        System.out.println(result);
    }

    @Test
    public void testOne() {
        Integer id = 1;
        Object res = rechargeController.read(id);
        System.out.println(res);
    }
}
