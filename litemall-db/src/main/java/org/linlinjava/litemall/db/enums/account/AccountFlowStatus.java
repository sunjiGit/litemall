package org.linlinjava.litemall.db.enums.account;

public enum AccountFlowStatus {

    SUBMIT("SUBMIT", "提交充值"),
    CONFIRM("CONFIRM", "确认充值"),
    INVALID("INVALID", "作废"),
    ;

    private String code;
    private String desc;

    AccountFlowStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
