package org.linlinjava.litemall.wx.util;

import org.linlinjava.litemall.db.enums.account.AccountFlowType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class PayUtil implements Serializable {


    public static String fenToYuan(Integer fen) {
        return BigDecimal.valueOf(Double.valueOf((double) fen) / 100.0D).setScale(2, 4).toPlainString();
    }


    public static Integer YuanToFen(BigDecimal yuan) {
        return yuan.multiply(BigDecimal.valueOf(100)).intValue();
    }

    public static String uniqFlowId(Integer userId, AccountFlowType type) {
        // 账户流水 uid + timestamp
        return String.format("%d-%d-%s-%d",
                userId,
                type.getOrder(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
                (long) (Math.random() * 1000));
    }
}
