package org.linlinjava.litemall.db.util;

import org.linlinjava.litemall.db.domain.LitemallOrder;

import java.util.ArrayList;
import java.util.List;

/*
 * 拼单流程：首单支付=开拼单－》分享－》他人下单=加入拼单-》确认拼单-》发货-》确认收货
 * 订单状态：
 * 201 拼单生成，首单已支付；
 * 202，订单生产，已付款未发货，所有拼单用户均申请退款；
 * 301 商家发货；
 *
 * 当201支付完成而商家未发货时，此时用户可以退款
 * 当202所有拼单退款时，拼单结束
 * 当301商家已发货时，拼单结束
 */
public class GropuOrderUtil {

    public static final Short STATUS_CREATE = 201;
    public static final Short STATUS_REFUND = 202;
    public static final Short STATUS_SHIP = 301;



//    public static String orderStatusText(LitemallOrder order) {
//        int status = order.getOrderStatus().intValue();
//
//        if (status == 101) {
//            return "未付款";
//        }
//
//        if (status == 102) {
//            return "已取消";
//        }
//
//        if (status == 103) {
//            return "已取消(系统)";
//        }
//
//        if (status == 200) {
//            return "已付款团购";
//        }
//
//        if (status == 201) {
//            return "已付款";
//        }
//
//        if (status == 202) {
//            return "订单取消，退款中";
//        }
//
//        if (status == 203) {
//            return "已退款";
//        }
//
//        if (status == 204) {
//            return "已超时团购";
//        }
//
//        if (status == 301) {
//            return "已发货";
//        }
//
//        if (status == 401) {
//            return "已收货";
//        }
//
//        if (status == 402) {
//            return "已收货(系统)";
//        }
//
//        throw new IllegalStateException("orderStatus不支持");
//    }
//
//
//    public static OrderHandleOption build(LitemallOrder order) {
//        int status = order.getOrderStatus().intValue();
//        OrderHandleOption handleOption = new OrderHandleOption();
//
//        if (status == 101) {
//            // 如果订单没有被取消，且没有支付，则可支付，可取消
//            handleOption.setCancel(true);
//            handleOption.setPay(true);
//        } else if (status == 102 || status == 103) {
//            // 如果订单已经取消或是已完成，则可删除
//            handleOption.setDelete(true);
//        } else if (status == 200 || status == 201) {
//            // 如果订单已付款，没有发货，则可退款
//            handleOption.setRefund(true);
//        } else if (status == 202 || status == 204) {
//            // 如果订单申请退款中，没有相关操作
//        } else if (status == 203) {
//            // 如果订单已经退款，则可删除
//            handleOption.setDelete(true);
//        } else if (status == 301) {
//            // 如果订单已经发货，没有收货，则可收货操作,
//            // 此时不能取消订单
//            handleOption.setConfirm(true);
//        } else if (status == 401 || status == 402) {
//            // 如果订单已经支付，且已经收货，则可删除、去评论和再次购买
//            handleOption.setDelete(true);
//            handleOption.setComment(true);
//            handleOption.setRebuy(true);
//        } else {
//            throw new IllegalStateException("status不支持");
//        }
//
//        return handleOption;
//    }
//
//    public static List<Short> orderStatus(Integer showType) {
//        // 全部订单
//        if (showType == 0) {
//            return null;
//        }
//
//        List<Short> status = new ArrayList<Short>(2);
//
//        if (showType.equals(1)) {
//            // 待付款订单
//            status.add((short) 101);
//        } else if (showType.equals(2)) {
//            // 待发货订单
//            status.add((short) 201);
//        } else if (showType.equals(3)) {
//            // 待收货订单
//            status.add((short) 301);
//        } else if (showType.equals(4)) {
//            // 待评价订单
//            status.add((short) 401);
////            系统超时自动取消，此时应该不支持评价
////            status.add((short)402);
//        } else {
//            return null;
//        }
//
//        return status;
//    }
//
//
//    public static boolean isCreateStatus(LitemallOrder litemallOrder) {
//        return GropuOrderUtil.STATUS_CREATE == litemallOrder.getOrderStatus().shortValue();
//    }
//
//    public static boolean hasPayed(LitemallOrder order) {
//        return GropuOrderUtil.STATUS_CREATE != order.getOrderStatus().shortValue()
//                && GropuOrderUtil.STATUS_CANCEL != order.getOrderStatus().shortValue()
//                && GropuOrderUtil.STATUS_AUTO_CANCEL != order.getOrderStatus().shortValue();
//    }
//
//    public static boolean isPayStatus(LitemallOrder litemallOrder) {
//        return GropuOrderUtil.STATUS_PAY == litemallOrder.getOrderStatus().shortValue();
//    }
//
//    public static boolean isShipStatus(LitemallOrder litemallOrder) {
//        return GropuOrderUtil.STATUS_SHIP == litemallOrder.getOrderStatus().shortValue();
//    }
//
//    public static boolean isConfirmStatus(LitemallOrder litemallOrder) {
//        return GropuOrderUtil.STATUS_CONFIRM == litemallOrder.getOrderStatus().shortValue();
//    }
//
//    public static boolean isCancelStatus(LitemallOrder litemallOrder) {
//        return GropuOrderUtil.STATUS_CANCEL == litemallOrder.getOrderStatus().shortValue();
//    }
//
//    public static boolean isAutoCancelStatus(LitemallOrder litemallOrder) {
//        return GropuOrderUtil.STATUS_AUTO_CANCEL == litemallOrder.getOrderStatus().shortValue();
//    }
//
//    public static boolean isRefundStatus(LitemallOrder litemallOrder) {
//        return GropuOrderUtil.STATUS_REFUND == litemallOrder.getOrderStatus().shortValue();
//    }
//
//    public static boolean isRefundConfirmStatus(LitemallOrder litemallOrder) {
//        return GropuOrderUtil.STATUS_REFUND_CONFIRM == litemallOrder.getOrderStatus().shortValue();
//    }
//
//    public static boolean isAutoConfirmStatus(LitemallOrder litemallOrder) {
//        return GropuOrderUtil.STATUS_AUTO_CONFIRM == litemallOrder.getOrderStatus().shortValue();
//    }
}
