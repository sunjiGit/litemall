package org.linlinjava.litemall.wx.wxController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.db.domain.LitemallAccount;
import org.linlinjava.litemall.db.domain.LitemallAccountFlow;
import org.linlinjava.litemall.wx.vo.CouponVo;
import org.linlinjava.litemall.wx.vo.FundCouponConfigVo;
import org.linlinjava.litemall.wx.web.WxAccountController;
import org.linlinjava.litemall.wx.web.WxCouponController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WxAccountControllerTest {

    private final Log logger = LogFactory.getLog(WxAccountControllerTest.class);

    @Autowired
    private WxAccountController wxAccountController;
    @Autowired
    private WxCouponController wxCouponController;

    @Test
    public void testListFundConfig() {
        int userId = 1;
        int pageNum = 1;
        int pageSize = 10;
        Map<String, Object> fundConfigList = (Map<String, Object>) wxAccountController.listFundConfig(userId, pageNum,
                pageSize);
        List<FundCouponConfigVo> vos = (ArrayList<FundCouponConfigVo>)
                ((Map<String, Object>) fundConfigList.get("data")).get("list");
        System.out.println("fund config list=" + vos);
    }

    @Test
    public void testFundByConfig() {
        int userId = 3;
        // 查充值余额
        Map<String, Object> accountInfo = (Map<String, Object>) wxAccountController.info(userId);
        System.out.println("accountInfo=" + accountInfo.get("data"));
        Map<String, Object> myCouponList = (Map<String, Object>) wxCouponController.mylist(userId,
                Short.parseShort("0"), 1, 10, "add_time", "desc");
        System.out.println("myCouponList=" + myCouponList);
        List<CouponVo> couponVos = (List<CouponVo>) ((Map<String, Object>) myCouponList.get("data")).get("list");
        System.out.println("couponList=" + JacksonUtil.toJson(couponVos));

//        wxAccountController.listFundConfig(userId, 1, 10);
//        int configId = 1;
//        // 调用充值
//        Object result = wxAccountController.fundByConfig(userId, configId);
//        System.out.println("userId=" + userId + ", configId=" + configId + ", funcConfig result=" + result);
//
//        // 查充值余额
//        accountInfo = (Map<String, Object>) wxAccountController.info(userId);
//        System.out.println("after fundByConfig...");
//        System.out.println("accountInfo=" + accountInfo.get("data"));
//        // 查充值流水
//        Map<String, Object> flowResult = (Map<String, Object>) wxAccountController.flow(userId, 1, 20);
//        System.out.println("flowResult=" + flowResult);
//        List<LitemallAccountFlow> list = (List<LitemallAccountFlow>) ((Map<String, Object>) flowResult.get("data")).get("list");
//        System.out.println("flows=" + JacksonUtil.toJson(list));
//        // 查优惠券
//        myCouponList = (Map<String, Object>) wxCouponController.mylist(userId,
//                Short.parseShort("0"), 1, 10, "add_time", "desc");
//        System.out.println("myCouponList=" + myCouponList);
//        couponVos = (List<CouponVo>) ((Map<String, Object>) myCouponList.get("data")).get("list");
//        System.out.println("couponList=" + JacksonUtil.toJson(couponVos));

    }

    @Test
    public void fundAndInfoTest() {
        int userId = 3;
        Map<String, Object> object = (Map<String, Object>) wxAccountController.info(userId);
        LitemallAccount account = (LitemallAccount) object.get("data");
        System.out.println("account=" + account);

        Map<String, Object> flowList = (Map<String, Object>) wxAccountController.flow(userId, 1, 2);
        List<LitemallAccountFlow> flows = (ArrayList<LitemallAccountFlow>) ((HashMap<String, Object>) flowList.get("data")).get("list");
        System.out.println("flows=" + JacksonUtil.toJson(flows));

        LitemallAccountFlow fundFlow = new LitemallAccountFlow();
        fundFlow.setUserId(userId);
        fundFlow.setAccountId(account.getId());
        fundFlow.setAmount(10000);
        object = (Map<String, Object>) wxAccountController.fund(userId, fundFlow);
        String uniqFlowId = object.get("data").toString();
        System.out.println(uniqFlowId);

        object = (Map<String, Object>) wxAccountController.info(userId);
        account = (LitemallAccount) object.get("data");
        System.out.println("account2=" + account);

        flowList = (Map<String, Object>) wxAccountController.flow(userId, 1, 2);
        flows = (ArrayList<LitemallAccountFlow>) ((HashMap<String, Object>) flowList.get("data")).get("list");
        System.out.println("flows2=" + JacksonUtil.toJson(flows));

    }


    @Test
    public void testRedEnvelop() {
        int userId = 3;
        int orderId = (int) System.currentTimeMillis();

        Map<String, Object> object = (Map<String, Object>) wxAccountController.info(userId);
        LitemallAccount account = (LitemallAccount) object.get("data");
        System.out.println("account=" + account);

        Map<String, Object> flowList = (Map<String, Object>) wxAccountController.flow(userId, 1, 2);
        List<LitemallAccountFlow> flows = (ArrayList<LitemallAccountFlow>) ((HashMap<String, Object>) flowList.get("data")).get("list");
        System.out.println("flows=" + JacksonUtil.toJson(flows));

        object = (Map<String, Object>) wxAccountController.redenvelop(userId, orderId);
        String uniqFlowId = object.get("data").toString();
        System.out.println(uniqFlowId);

        object = (Map<String, Object>) wxAccountController.info(userId);
        account = (LitemallAccount) object.get("data");
        System.out.println("account2=" + account);

        flowList = (Map<String, Object>) wxAccountController.flow(userId, 1, 2);
        flows = (ArrayList<LitemallAccountFlow>) ((HashMap<String, Object>) flowList.get("data")).get("list");
        System.out.println("flows2=" + JacksonUtil.toJson(flows));

        flowList = (Map<String, Object>) wxAccountController.flow(userId, 2, 2);
        flows = (ArrayList<LitemallAccountFlow>) ((HashMap<String, Object>) flowList.get("data")).get("list");
        System.out.println("flows3=" + JacksonUtil.toJson(flows));

    }
}
