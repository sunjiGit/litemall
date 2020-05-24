package org.linlinjava.litemall.core.printer;

import org.linlinjava.litemall.core.printer.jiabo.JiaboSendMsg;
import org.springframework.stereotype.Component;

public class CloudPrinter {

    private JiaboSendMsg jiaboSendMsg;


    public JiaboSendMsg getJiaboSendMsg() {
        return jiaboSendMsg;
    }

    public void setJiaboSendMsg(JiaboSendMsg jiaboSendMsg) {
        this.jiaboSendMsg = jiaboSendMsg;
    }
}
