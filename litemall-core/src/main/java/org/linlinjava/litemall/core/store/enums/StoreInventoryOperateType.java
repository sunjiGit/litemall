package org.linlinjava.litemall.core.store.enums;

public enum StoreInventoryOperateType {

    ORDER_OUT("ORDER_OUT", "订单卖出"),
    MAKE_IN("MAKE_IN", "制作收入"),
    ADJUST_OUT("ADJUST_OUT", "调整减少"),
    ADJUST_IN("ADJUST_IN", "调整增加"),
    ;

    private String code;
    private String desc;

    StoreInventoryOperateType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static StoreInventoryOperateType getByCode(String code) {
        for (StoreInventoryOperateType st : StoreInventoryOperateType.values()) {
            if (st.getCode().equalsIgnoreCase(code)) {
                return st;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
