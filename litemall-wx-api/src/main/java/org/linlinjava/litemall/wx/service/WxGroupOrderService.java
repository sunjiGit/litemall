package org.linlinjava.litemall.wx.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallGroupOrder;
import org.linlinjava.litemall.db.domain.LitemallOrder;
import org.linlinjava.litemall.db.service.LitemallGroupOrderService;
import org.linlinjava.litemall.db.service.LitemallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拼单订单服务
 */
@Service
public class WxGroupOrderService {
    private final Log logger = LogFactory.getLog(WxGroupOrderService.class);

    @Autowired
    private LitemallGroupOrderService groupOrderService;
    @Autowired
    private LitemallOrderService orderService;

    /**
     * 创建拼单
     * <p>
     * 创建拼单数据库记录
     *
     * @param userId
     * @param body
     * @return
     */
    public Object createGroupOrder(Integer userId, String body) {
        logger.info(String.format("create group order param userId=%d, body=%s", userId, body));

        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (body == null) {
            return ResponseUtil.badArgument();
        }
        String address = JacksonUtil.parseString(body, "address");
        if (StringUtils.isBlank(address)) {
            return ResponseUtil.badArgument();
        }

        Integer groupOrderId;
        LitemallGroupOrder groupOrder = new LitemallGroupOrder();
        groupOrder.setUserId(userId);
        groupOrder.setAddress(address);

        groupOrder.setOrderId(0); // 不一定有订单ID了
        //TODO 拼单缺少状态，用于不同
//        groupOrder.setStatus();

        LocalDateTime now = LocalDateTime.now();
        groupOrder.setStartTime(now);
        groupOrder.setEndTime(now.plusMinutes(5));
        groupOrder.setAddTime(now);
        groupOrder.setUpdateTime(now);

        try {
            groupOrderService.add(groupOrder);
            groupOrderId = groupOrder.getId();

            Map<String, Object> data = new HashMap<>();
            data.put("groupOrderId", groupOrderId);
            Object result = ResponseUtil.ok(data);
            logger.info(String.format("create group order result=%s, param userId=%d, body=%s",
                    result, userId, body));
            return result;
        } catch (Exception e) {
            logger.error("create group order error.", e);
            return ResponseUtil.serious();
        }
    }

    /**
     * 加入拼单
     *
     * @param userId
     * @param body
     * @return
     */
    public Object joinGroupOrder(Integer userId, String body) {
        logger.info(String.format("join group order param userId=%d, body=%s", userId, body));

        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (body == null) {
            return ResponseUtil.badArgument();
        }
        Integer groupOrderId = JacksonUtil.parseInteger(body, "groupOrderId");
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        if (groupOrderId == null || orderId == null) {
            return ResponseUtil.badArgument();
        }

        try {
            LitemallGroupOrder groupOrder = groupOrderService.findById(groupOrderId);
            if (groupOrder == null) {// TODO 或者状态不对
                logger.info(String.format("join group order is null. no groupOrderId=%d", groupOrderId));
                return ResponseUtil.badArgumentNoData();
            }

            // orderId TODO 考虑是否冗余订单ID

            Map<String, Object> data = new HashMap<>();
            data.put("groupOrderId", groupOrderId);
            Object result = ResponseUtil.ok(data);
            logger.info(String.format("join group order result=%s, param userId=%d, body=%s",
                    result, userId, body));
            return result;
        } catch (Exception e) {
            logger.error("join group order error.", e);
            return ResponseUtil.serious();
        }
    }

    /**
     * 获取拼单的具体地址
     *
     * @param userId
     * @param body
     * @return
     */
    public Object getGroupOrderAddress(Integer userId, String body) {
        logger.info(String.format("get group order address param userId=%d, body=%s", userId, body));

        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (body == null) {
            return ResponseUtil.badArgument();
        }
        Integer groupOrderId = JacksonUtil.parseInteger(body, "groupOrderId");
        if (groupOrderId == null) {
            return ResponseUtil.badArgument();
        }

        try {
            LitemallGroupOrder groupOrder = groupOrderService.findById(groupOrderId);
            if (groupOrder == null) {// TODO 或者状态不对
                logger.info(String.format("get group order address is null. groupOrderId=%d", groupOrderId));
                return ResponseUtil.badArgumentNoData();
            }

            Map<String, Object> data = new HashMap<>();
            data.put("address", groupOrder.getAddress());
            Object result = ResponseUtil.ok(data);
            logger.info(String.format("get group order address result=%s, param userId=%d, body=%s",
                    result, userId, body));
            return result;
        } catch (Exception e) {
            logger.error("get group order address error.", e);
            return ResponseUtil.serious();
        }
    }

    /**
     * 获取拼单的所有订单
     *
     * @param userId
     * @param body
     * @return
     */
    public Object getGroupOrderList(Integer userId, String body) {
        logger.info(String.format("get group order list param userId=%d, body=%s", userId, body));

        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (body == null) {
            return ResponseUtil.badArgument();
        }
        Integer groupOrderId = JacksonUtil.parseInteger(body, "groupOrderId");
        if (groupOrderId == null) {
            return ResponseUtil.badArgument();
        }

        try {
            LitemallGroupOrder groupOrder = groupOrderService.findById(groupOrderId);
            if (groupOrder == null) {// TODO 或者状态不对
                logger.info(String.format("get group order list is null. groupOrderId=%d", groupOrderId));
                return ResponseUtil.badArgumentNoData();
            }

            List<LitemallOrder> orderList = orderService.queryByGroupOrderId(groupOrderId);
            Object result = ResponseUtil.okList(orderList);
            logger.info(String.format("get group order list result=%s, param userId=%d, body=%s",
                    result, userId, body));
            return result;
        } catch (Exception e) {
            logger.error("get group order list error.", e);
            return ResponseUtil.serious();
        }
    }

    /**
     * 确认拼单
     */
    public Object confirmGroupOrder(Integer userId, String body) {
        logger.info(String.format("confirm group order param userId=%d, body=%s", userId, body));

        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (body == null) {
            return ResponseUtil.badArgument();
        }
        Integer groupOrderId = JacksonUtil.parseInteger(body, "groupOrderId");
        if (groupOrderId == null) {
            return ResponseUtil.badArgument();
        }

        try {
            LitemallGroupOrder groupOrder = groupOrderService.findById(groupOrderId);
            if (groupOrder == null) {// TODO 或者状态不对
                logger.info(String.format("confirm group order is null. groupOrderId=%d", groupOrderId));
                return ResponseUtil.badArgumentNoData();
            }

//TODO 修改状态
//            groupOrderService.confirm(groupOrderId);

            Object result = ResponseUtil.ok();
            logger.info(String.format("confirm group order result=%s, param userId=%d, body=%s",
                    result, userId, body));
            return result;
        } catch (Exception e) {
            logger.error("confirm group order error.", e);
            return ResponseUtil.serious();
        }
    }

    /**
     * 取消拼单
     */
    public Object cancelGroupOrder(Integer userId, String body) {
        logger.info(String.format("cancel group order param userId=%d, body=%s", userId, body));

        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (body == null) {
            return ResponseUtil.badArgument();
        }
        Integer groupOrderId = JacksonUtil.parseInteger(body, "groupOrderId");
        if (groupOrderId == null) {
            return ResponseUtil.badArgument();
        }

        try {
            LitemallGroupOrder groupOrder = groupOrderService.findById(groupOrderId);
            if (groupOrder == null) {// TODO 或者状态不对
                logger.info(String.format("cancel group order is null. groupOrderId=%d", groupOrderId));
                return ResponseUtil.badArgumentNoData();
            }

//TODO 修改状态
//            groupOrderService.confirm(groupOrderId);



            Object result = ResponseUtil.ok();
            logger.info(String.format("cancel group order result=%s, param userId=%d, body=%s",
                    result, userId, body));
            return result;
        } catch (Exception e) {
            logger.error("cancel group order error.", e);
            return ResponseUtil.serious();
        }
    }


//    /**
//     * 取消订单
//     * <p>
//     * 1. 检测当前订单是否能够取消；
//     * 2. 设置订单取消状态；
//     * 3. 商品货品库存恢复；
//     * 4. TODO 优惠券；
//     * 5. TODO 团购活动。
//     *
//     * @param userId 用户ID
//     * @param body   订单信息，{ orderId：xxx }
//     * @return 取消订单操作结果
//     */
//    @Transactional
//    public Object cancel(Integer userId, String body) {
//        if (userId == null) {
//            return ResponseUtil.unlogin();
//        }
//        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
//        if (orderId == null) {
//            return ResponseUtil.badArgument();
//        }
//
//        LitemallOrder order = orderService.findById(orderId);
//        if (order == null) {
//            return ResponseUtil.badArgumentValue();
//        }
//        if (!order.getUserId().equals(userId)) {
//            return ResponseUtil.badArgumentValue();
//        }
//
//        LocalDateTime preUpdateTime = order.getUpdateTime();
//
//        // 检测是否能够取消
//        OrderHandleOption handleOption = OrderUtil.build(order);
//        if (!handleOption.isCancel()) {
//            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能取消");
//        }
//
//        // 设置订单已取消状态
//        order.setOrderStatus(OrderUtil.STATUS_CANCEL);
//        order.setEndTime(LocalDateTime.now());
//        if (orderService.updateWithOptimisticLocker(order) == 0) {
//            throw new RuntimeException("更新数据已失效");
//        }
//
//        // 商品货品数量增加
//        List<LitemallOrderGoods> orderGoodsList = orderGoodsService.queryByOid(orderId);
//        for (LitemallOrderGoods orderGoods : orderGoodsList) {
//            Integer productId = orderGoods.getProductId();
//            Short number = orderGoods.getNumber();
//            if (productService.addStock(productId, number) == 0) {
//                throw new RuntimeException("商品货品库存增加失败");
//            }
//        }
//
//        return ResponseUtil.ok();
//    }
//
//    /**
//     * 删除订单
//     * <p>
//     * 1. 检测当前订单是否可以删除；
//     * 2. 删除订单。
//     *
//     * @param userId 用户ID
//     * @param body   订单信息，{ orderId：xxx }
//     * @return 订单操作结果
//     */
//    public Object delete(Integer userId, String body) {
//        if (userId == null) {
//            return ResponseUtil.unlogin();
//        }
//        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
//        if (orderId == null) {
//            return ResponseUtil.badArgument();
//        }
//
//        LitemallOrder order = orderService.findById(orderId);
//        if (order == null) {
//            return ResponseUtil.badArgument();
//        }
//        if (!order.getUserId().equals(userId)) {
//            return ResponseUtil.badArgumentValue();
//        }
//
//        OrderHandleOption handleOption = OrderUtil.build(order);
//        if (!handleOption.isDelete()) {
//            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能删除");
//        }
//
//        // 订单order_status没有字段用于标识删除
//        // 而是存在专门的delete字段表示是否删除
//        orderService.deleteById(orderId);
//
//        return ResponseUtil.ok();
//    }


}