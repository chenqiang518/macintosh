package com.vernon.poppy.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AccessTokenDTO {
    @Getter
    private static final AccessTokenDTO instance = new AccessTokenDTO();
    private String corpid;
    private String corpsecret;

    protected AccessTokenDTO() {
    }

    @Override
    public String toString() {
        return "AccessTokenDTO{" +
                "corpid='" + corpid + '\'' +
                ", corpsecret='" + corpsecret + '\'' +
                '}';
    }
}
