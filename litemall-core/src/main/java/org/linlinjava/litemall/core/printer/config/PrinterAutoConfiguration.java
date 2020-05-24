package org.linlinjava.litemall.core.printer.config;

import org.linlinjava.litemall.core.printer.CloudPrinter;
import org.linlinjava.litemall.core.printer.jiabo.JiaboSendMsg;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PrinterProperties.class)
public class PrinterAutoConfiguration {

    private final PrinterProperties properties;

    public PrinterAutoConfiguration(PrinterProperties properties) {
        this.properties = properties;
    }

    @Bean
    public CloudPrinter cloudPrinter() {
        CloudPrinter cp = new CloudPrinter();
        cp.setJiaboSendMsg(jiaboSendMsg());
        // 如需支持多个云打印机 请参考 storageConfig 的写法。
        return cp;
    }

    @Bean
    public JiaboSendMsg jiaboSendMsg() {
        JiaboSendMsg jiaboSendMsg = new JiaboSendMsg();
        PrinterProperties.Jiabo jiabo = this.properties.getJiabo();
        jiaboSendMsg.setApiKey(jiabo.getApiKey());
        jiaboSendMsg.setMemberCode(jiabo.getMemberCode());
        jiaboSendMsg.setSendUrl(jiabo.getSendUrl());
        jiaboSendMsg.setQueryUrl(jiabo.getQueryUrl());
        return jiaboSendMsg;
    }
}
