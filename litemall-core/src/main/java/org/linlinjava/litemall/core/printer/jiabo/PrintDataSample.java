package org.linlinjava.litemall.core.printer.jiabo;

@Deprecated
public interface PrintDataSample {

    // Jiabo 标准样式 订单样式1=

    /*
    多半欢迎您
    时间：
            === 订单信息 ===
    门店单号：
    订单流水号：
            === 商品信息 ===
    商品名称      数量 金额
    暴雨梨花汁     1  20.00
    雨前玫瑰茶     2  50.00

    总金额			 70.00
            === 支付信息 ===
    会员折扣          -10
    代金券           -15
    实付合计          45
            === 物流信息 ===
    电话：
    地址：
    收货人：
     */
    String orderPrinterData = "<gpWord Align=1 Bold=0 Wsize=0 Hsize=0 Reverse=0 Underline=0>--------------------------------</gpWord>\n" +
            "<gpWord Align=1 Bold=0 Wsize=1 Hsize=1 Reverse=0 Underline=0>多半欢迎您</gpWord>\n" +
            "<gpWord Align=0 Bold=0 Wsize=0 Hsize=0 Reverse=0 Underline=0>时间：2020-05-24 16:01:05</gpWord>\n" +
            "<gpWord Align=0 Bold=1 Wsize=0 Hsize=0 Reverse=0 Underline=0>门店单号：078</gpWord>\n" +
            "<gpWord Align=0 Bold=0 Wsize=0 Hsize=0 Reverse=0 Underline=0>订单流水号：20200524160105001078</gpWord>\n" +
            "<gpBr/>\n" +
            "<gpTR4 Type=0><td>商品名称</td><td></td><td>数量</td><td>金额</td></gpTR4>\n" +
            "<gpTR4 Type=0><td>暴雨梨花汁</td><td></td><td>1</td><td>20.00</td></gpTR4>\n" +
            "<gpTR4 Type=0><td>雨前玫瑰茶</td><td></td><td>2</td><td>50.00</td></gpTR4>\n" +
            "<gpBr/>\n" +
            "<gpTR4 Type=0><td>会员折扣</td><td></td><td></td><td>-10.00</td></gpTR4>\n" +
            "<gpTR4 Type=0><td>代金券</td><td></td><td></td><td>-15.00</td></gpTR4>\n" +
            "<gpTR4 Type=0><td>实付合计</td><td></td><td></td><td>45.00</td></gpTR4>\n" +
            "<gpBr/>\n" +
            "<gpWord Align=0 Bold=1 Wsize=0 Hsize=0 Reverse=0 Underline=0>收货人：孙先生</gpWord>\n" +
            "<gpWord Align=0 Bold=1 Wsize=0 Hsize=0 Reverse=0 Underline=0>电话：159****6156</gpWord>\n" +
            "<gpWord Align=0 Bold=1 Wsize=0 Hsize=0 Reverse=0 Underline=0>地址：</gpWord>\n" +
            "<gpWord Align=0 Bold=1 Wsize=0 Hsize=0 Reverse=0 Underline=0>红石路730弄汇丰凯苑25号楼103</gpWord>\n" +
            "<gpWord Align=1 Bold=0 Wsize=0 Hsize=0 Reverse=0 Underline=0>--------------------------------</gpWord>\n" +
            "<gpBr/>\n" +
            "<gpCut/>\n";


    //		================== 后厨订单 ============================
	/*
	多半配单

	时间：
			=== 订单信息 ===

	门店单号：
	订单流水号：

			=== 商品信息 ===
	商品名称      数量
	暴雨梨花汁     1
	雨前玫瑰茶     2
	*/
    //		================= 以上是模版 =====================
    //		================= 以下是翻译 - 打印机 =====================

    String orderPreparePrinterData = "<gpWord Align=1 Bold=0 Wsize=0 Hsize=0 Reverse=0 Underline=0>--------------------------------</gpWord>\n" +
            "<gpWord Align=1 Bold=0 Wsize=1 Hsize=1 Reverse=0 Underline=0>多半配单</gpWord>\n" +
            "<gpWord Align=0 Bold=0 Wsize=0 Hsize=0 Reverse=0 Underline=0>时间：2020-05-24 16:01:05</gpWord>\n" +
            "<gpWord Align=0 Bold=1 Wsize=0 Hsize=0 Reverse=0 Underline=0>门店单号：078</gpWord>\n" +
            "<gpWord Align=0 Bold=0 Wsize=0 Hsize=0 Reverse=0 Underline=0>订单流水号：20200524160105001078</gpWord>\n" +
            "<gpBr/>\n" +
            "<gpTR2 Type=0><td>商品名称</td><td>数量</td></gpTR2>\n" +
            "<gpTR2 Type=0><td>暴雨梨花汁</td><td>1</td></gpTR2>\n" +
            "<gpTR2 Type=0><td>雨前玫瑰茶</td><td>2</td></gpTR2>\n" +
            "<gpWord Align=1 Bold=0 Wsize=0 Hsize=0 Reverse=0 Underline=0>--------------------------------</gpWord>\n" +
            "<gpBr/>";

//	佳博 智能外卖点餐解决方案GP-58130IVC云打印机美团/饿了么自动接单神器  这个机器有切刀，能自动切纸。
//
//	飞印 https://www.feyin.cn/feyinapi/
//	飞鹅 http://www.feieyun.com/open/index.html?name=1  京东有商品，估计这家比较大。
//	易联云 https://www.yilianyun.net/  京东有商品，估计这家比较大。

}
