package org.linlinjava.litemall.core.store.enums;

public enum StoreStatus {

    WORKING("WORKING", "营业中"),
    STOP("STOP", "打烊"),
    ;

    private String code;
    private String desc;

    StoreStatus(String code, String desc) {
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
