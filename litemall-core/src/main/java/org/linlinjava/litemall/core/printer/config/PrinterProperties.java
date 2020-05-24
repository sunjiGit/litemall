package org.linlinjava.litemall.core.printer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "litemall.printer")
public class PrinterProperties {
    private Jiabo jiabo;

    public Jiabo getJiabo() {
        return jiabo;
    }

    public void setJiabo(Jiabo jiabo) {
        this.jiabo = jiabo;
    }

    public static class Jiabo {
        private String memberCode;
        private String apiKey;
        private String sendUrl;
        private String queryUrl;

        public String getMemberCode() {
            return memberCode;
        }

        public void setMemberCode(String memberCode) {
            this.memberCode = memberCode;
        }

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getSendUrl() {
            return sendUrl;
        }

        public void setSendUrl(String sendUrl) {
            this.sendUrl = sendUrl;
        }

        public String getQueryUrl() {
            return queryUrl;
        }

        public void setQueryUrl(String queryUrl) {
            this.queryUrl = queryUrl;
        }
    }
}
