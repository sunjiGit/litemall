package org.linlinjava.litemall.wx.wxController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.db.domain.LitemallAccount;
import org.linlinjava.litemall.db.domain.LitemallAccountFlow;
import org.linlinjava.litemall.wx.web.WxAccountController;
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
