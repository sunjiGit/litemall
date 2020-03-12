package org.linlinjava.litemall.core.store.enums;

public enum StoreType {

    TAKE_OUT("TAKE_OUT", "外面店"),
    DINE_IN("DINE_IN", "堂食店"),
    ;

    private String code;
    private String desc;

    StoreType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static StoreType getByCode(String code) {
        for (StoreType st : StoreType.values()) {
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
