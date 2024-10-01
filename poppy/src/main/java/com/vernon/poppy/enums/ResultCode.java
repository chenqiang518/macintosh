package com.vernon.poppy.enums;

import lombok.Getter;
public enum ResultCode {

    SUCCESS(200,"success"),
    ERROR(-1,"fail");

    private final int code;
    private final String message;

    ResultCode(int code,String message){
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

