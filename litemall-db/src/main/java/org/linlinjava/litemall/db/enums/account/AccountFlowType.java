package org.linlinjava.litemall.db.enums.account;

public enum AccountFlowType {

    RECHARGE(1, "RECHARGE", "充值"),
    CONSUME(2, "CONSUME", "消费"),
    RED_ENVELOPE(3, "RED_ENVELOPE", "红包充值"),
    ;

    private Integer order;
    private String code;
    private String desc;

    AccountFlowType(Integer order, String code, String desc) {
        this.order = order;
        this.code = code;
        this.desc = desc;
    }

    public Integer getOrder() {
        return order;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
