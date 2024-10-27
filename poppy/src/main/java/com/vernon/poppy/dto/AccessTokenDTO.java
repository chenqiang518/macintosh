package com.vernon.poppy.dto;

public class AccessTokenDTO {
    private static final AccessTokenDTO instance = new AccessTokenDTO();
    private String corpid;
    private String corpsecret;

    protected AccessTokenDTO() {
    }

    public static AccessTokenDTO getInstance() {
        return instance;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public void setCorpsecret(String corpsecret) {
        this.corpsecret = corpsecret;
    }

    public String getCorpid() {
        return corpid;
    }

    public String getCorpsecret() {
        return corpsecret;
    }

    @Override
    public String toString() {
        return "AccessTokenDTO{" +
                "corpid='" + corpid + '\'' +
                ", corpsecret='" + corpsecret + '\'' +
                '}';
    }
}
