package org.linlinjava.litemall.core.printer.jiabo;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.notify.TencentSmsSender;
import org.linlinjava.litemall.db.domain.LitemallOrder;
import org.linlinjava.litemall.db.domain.LitemallOrderGoods;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 票据打印（对应标签打印）
 */
@SuppressWarnings("DuplicatedCode")
public class JiaboSendMsg {

    private final Log logger = LogFactory.getLog(TencentSmsSender.class);

    private String memberCode;
    private String apiKey;
    private String sendUrl;
    private String queryUrl;

    /**
     * 用户侧，订单
     */
    public void printOrder(String orderPrinterCode, LitemallOrder order, List<LitemallOrderGoods> orderGoods) {
        String reqTime = System.currentTimeMillis() + "";
        String msgNo = reqTime;
        Map<String, String> params = generateSendParams(orderPrinterCode, reqTime, msgNo);

        params.put("msgDetail", orderPrintData(order, orderGoods));

        logger.info("=====请求参数 START=====\r\n" + "---> sendMsg <---\r\n" + params + "\r\n=====请求参数 END=====");
        String result = Request.sendPost(sendUrl, params);
        logger.info("=====请求返回 START=====\r\n" + result + "\r\n=====请求返回 END=====");

        params.clear();
        params = generateGetStateParams(reqTime, msgNo);
        logger.info("=====请求参数 START=====\r\n" + "---> queryState <---\r\n" + params + "\r\n=====请求参数 END=====");
        result = Request.sendGet(queryUrl, params);
        logger.info("=====请求返回 START=====\r\n" + result + "\r\n=====请求返回 END=====");
    }

    /**
     * 后厨端，配料
     */
    public void printOrderPrepare(String preparePrinterCode, LitemallOrder order, List<LitemallOrderGoods> orderGoods) {
        String reqTime = System.currentTimeMillis() + "";
        String msgNo = reqTime;
        Map<String, String> params = generateSendParams(preparePrinterCode, reqTime, msgNo);

        params.put("msgDetail", prepareOrderPrintData(order, orderGoods));

        logger.info("=====请求参数 START=====\r\n" + "---> sendMsg <---\r\n" + params + "\r\n=====请求参数 END=====");
        String result = Request.sendPost(sendUrl, params);
        logger.info("=====请求返回 START=====\r\n" + result + "\r\n=====请求返回 END=====");

        params.clear();
        params = generateGetStateParams(reqTime, msgNo);
        logger.info("=====请求参数 START=====\r\n" + "---> queryState <---\r\n" + params + "\r\n=====请求参数 END=====");
        result = Request.sendGet(queryUrl, params);
        logger.info("=====请求返回 START=====\r\n" + result + "\r\n=====请求返回 END=====");
    }

    /**
     * 最终样式 参考 PrintDataSample 最后的部分。
     */
    private String orderPrintData(LitemallOrder order, List<LitemallOrderGoods> orderGoods) {
        PrintDataBuilder builder = new PrintDataBuilder();
        builder.addTitle("多半欢迎您")
                .addWord("时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .addBoldWord("门店单号：" + order.getOrderSn())
                .addWord("订单流水号：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + order.getStoreId() + order.getOrderSn())
                .addBR()
                .addGoodsTitle();

        for (LitemallOrderGoods orderGood : orderGoods) {
            BigDecimal price = orderGood.getPrice().setScale(2, RoundingMode.DOWN);
            builder.addTable4(orderGood.getGoodsName(), "", orderGood.getNumber().toString(),
                    price.toPlainString());
        }

        BigDecimal discountPrice = order.getDiscountPrice().setScale(2, RoundingMode.DOWN);
        BigDecimal couponPrice = order.getCouponPrice().setScale(2, RoundingMode.DOWN);
        BigDecimal actualPrice = order.getActualPrice().setScale(2, RoundingMode.DOWN);

        builder.addBR()
                .addTable2("会员折扣", "-" + discountPrice.toPlainString())
                .addTable2("代金券", "-" + couponPrice.toPlainString())
                .addTable2("实付", actualPrice.toPlainString())
                .addBR()
                .addBoldWord("收货人：" + order.getConsignee())
                .addBoldWord("电话：" + order.getMobile())
                .addBoldWord("地址：")
                .addBoldWord(order.getAddress());

        return builder.toPrintString();
    }

    /**
     * 最终样式 参考 PrintDataSample 最后的部分。
     */
    private String prepareOrderPrintData(LitemallOrder order, List<LitemallOrderGoods> orderGoods) {
        PrintDataBuilder builder = new PrintDataBuilder();
        builder.addTitle("多半配单")
                .addWord("时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .addBoldWord("门店单号：" + order.getOrderSn())
                .addWord("订单流水号：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + order.getStoreId() + order.getOrderSn())
                .addBR()
                .addGoodsTitle();

        for (LitemallOrderGoods orderGood : orderGoods) {
            builder.addTable4(orderGood.getGoodsName(), "", "", orderGood.getNumber().toString());
        }

        return builder.toPrintString();
    }

    private Map<String, String> generateSendParams(String receiptDeviceNo, String reqTime, String msgNo) {
        Map<String, String> params = new HashMap<>();
        params.put("reqTime", reqTime);
        String securityCode = DigestUtils.md5Hex(memberCode + receiptDeviceNo + reqTime + reqTime + apiKey);
        params.put("securityCode", securityCode);
        params.put("memberCode", memberCode);
        params.put("deviceNo", receiptDeviceNo);
        params.put("mode", "2"); //票据打印机 格式类型:2
        params.put("charset", "1"); //Default:1, 1:GB18030, 2:GB2312, 3:GBK, 4:UTF-8, 5:Unicode, 6:ISO8859-1, 7:BIG5
        params.put("msgNo", msgNo);
        return params;
    }

    private Map<String, String> generateGetStateParams(String reqTime, String msgNo) {
        Map<String, String> params = new HashMap<>();
        params.put("reqTime", reqTime);
        String securityCode = DigestUtils.md5Hex(memberCode + reqTime + apiKey + reqTime);
        params.put("securityCode", securityCode);
        params.put("memberCode", memberCode);
        params.put("msgNo", msgNo);
        return params;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }
}
