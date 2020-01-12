package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.wx.annotation.LoginUser;
import org.linlinjava.litemall.wx.service.WxGroupOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/gorder")
@Validated
public class WxGroupOrderController {
    private final Log logger = LogFactory.getLog(WxGroupOrderController.class);

    @Autowired
    private WxGroupOrderService wxGroupOrderService;

    /**
     * 创建拼单
     *
     * @param userId 用户ID
     * @param body   拼单信息，{ address: XXX }
     * @return 提交拼单操作结果
     * {..., "data":{"groupOrderId": XXX}}
     */
    @PostMapping("create_group_order")
    public Object createGroupOrder(@LoginUser Integer userId, @RequestBody String body) {
        return wxGroupOrderService.createGroupOrder(userId, body);
    }

    /**
     * 加入拼单
     *
     * @param userId 用户ID
     * @param body   拼单信息，{ groupOrderId: XXX, orderId: XXX }
     * @return 提交拼单操作结果
     * {..., "data":{"groupOrderId": XXX}}
     */
    @PostMapping("join_group_order")
    public Object joinGroupOrder(@LoginUser Integer userId, @RequestBody String body) {
        return wxGroupOrderService.joinGroupOrder(userId, body);
    }

    /**
     * 获取拼单的具体地址
     *
     * @param userId 用户ID
     * @param body   拼单信息，{ groupOrderId: XXX }
     * @return 提交拼单操作结果
     * {..., "data":{"address": XXX}}
     *
     */
    @PostMapping("get_grp_odr_address")
    public Object getGroupOrderAddress(@LoginUser Integer userId, @RequestBody String body) {
        return wxGroupOrderService.getGroupOrderAddress(userId, body);
    }

    /**
     * 获取拼单的所有订单
     *
     * @param userId 用户ID
     * @param body   拼单信息，{ groupOrderId: XXX }
     * @return 提交拼单操作结果
     * {..., "data":{"total":9,"pages":1,"limit":10,"page":1,"list":[XXXX]}}
     */
    @PostMapping("get_grp_odr_list")
    public Object getGroupOrderList(@LoginUser Integer userId, @RequestBody String body) {
        return wxGroupOrderService.getGroupOrderList(userId, body);
    }

    /**
     * 取消拼单
     *
     * @param userId 用户ID
     * @param body   拼单ID，{ groupOrderId: XXX }
     * @return 提交拼单操作结果
     * {"errno":0,"errmsg":"成功"}
     */
    @PostMapping("confirm_grp_odr")
    @Deprecated
    public Object confirmGroupOrder(@LoginUser Integer userId, @RequestBody String body) {
        return wxGroupOrderService.confirmGroupOrder(userId, body);
    }

    /**
     * 确认拼单
     *
     * @param userId 用户ID
     * @param body   拼单ID，{ groupOrderId: XXX }
     * @return 提交拼单操作结果
     * {"errno":0,"errmsg":"成功"}
     */
    @PostMapping("cancel_grp_odr")
    @Deprecated
    public Object cancelGroupOrder(@LoginUser Integer userId, @RequestBody String body) {
        return wxGroupOrderService.cancelGroupOrder(userId, body);
    }
}