package com.vernon.poppy.exception;

public class ServiceException extends RuntimeException{
    /**
     * 业务错误码
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException( String message, Integer code) {
        this.code = code;
        this.message = message;
    }



    public Integer getCode() {
        return code;
    }


    public ServiceException setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ServiceException setMessage(String message) {
        this.message = message;
        return this;
    }

}

