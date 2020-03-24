package org.linlinjava.litemall.wx.wxController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.litemall.core.notify.AliyunSmsSender;
import org.linlinjava.litemall.core.notify.SmsResult;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallCategory;
import org.linlinjava.litemall.db.domain.LitemallGoods;
import org.linlinjava.litemall.db.service.LitemallCategoryService;
import org.linlinjava.litemall.db.service.LitemallGoodsService;
import org.linlinjava.litemall.wx.web.WxAuthController;
import org.omg.CORBA.PUBLIC_MEMBER;
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
public class WxAuthControllerTest {

    private final Log logger = LogFactory.getLog(WxAuthControllerTest.class);


    @Autowired
    private WxAuthController wxAuthController;
    @Autowired
    private AliyunSmsSender aliyunSmsSender;

    @Test
    public void test() {
        String phone = "15900986156";
        String templateCode = "SMS_181720394";
        String[] param = new String[1];
        param[0] = "123456";
        SmsResult smsResult = aliyunSmsSender.sendWithTemplate(phone, templateCode, param);
        System.out.println(smsResult);
    } //passed

    @Test
    public void aliSmsTest() {
        int userId = 3;
        String body = "{ \"mobile\": \"15900986156\", \"type\": \"captcha\" }";

        Object result = wxAuthController.captcha(userId, body);
        System.out.println(result);

        String code = "123456"; //真的要手机验证码啊
        String body2 = "{\"code\": \"" + code + "\",\"mobile\": \"15900986156\"}";
        result = wxAuthController.resetPhone(userId, body2, null);
        System.out.println(result);
    } // passed



}
