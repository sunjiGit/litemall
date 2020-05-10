package org.linlinjava.litemall.db.enums.user;

/**
 * 用户等级
 */
public enum UserLevel {
    NORMAL(0, "普通用户"),
    RECHARGE(1, "充值用户");

    int code;
    String desc;

    UserLevel(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
