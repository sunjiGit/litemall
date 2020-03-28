package org.linlinjava.litemall.db.enums.account;

public enum AccountStatus {

    NORMAL("NORMAL", "正常"),
    FREEZE("FREEZE", "冻结"),
    CANCEL("CANCEL", "销户"),
    ;

    private String code;
    private String desc;

    AccountStatus(String code, String desc) {
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
