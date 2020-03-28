package org.linlinjava.litemall.db.enums.account;

public enum AccountType {

    CASH("CASH", "现金"),
    ;

    private String code;
    private String desc;

    AccountType(String code, String desc) {
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
