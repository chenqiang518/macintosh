package com.vernon.poppy.util;

import com.vernon.poppy.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;


public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * {
     * 	"success": true,
     *     "code": 0,
     *     "msg": "成功",
     *     "data": {
     *         "user_remedys": [
     *             {
     *                 "user_id": "abd754f7",
     *                 "work_type": 1,
     *                 "status": 2,
     *                 "reason": "忘记打卡",
     *             }
     *         ]
     *     }
     * }
     * data:{}
     */
    private int code;
    private String message;
    private T data;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private R(){}

    public static <T> R<T> ok(){
        R<T> r = new R<>();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage("成功");
        return r;
    }

    public static <T> R<T> error(){
        R<T> r = new R<>();
        r.setCode(ResultCode.ERROR.getCode());
        r.setMessage("失败");
        r.setData((T)new HashMap<>());
        return r;
    }



    public R<T> message(String message){
        this.setMessage(message);
        return this;
    }

    public R<T> code(Integer code){
        this.setCode(code);
        return this;
    }


    public R<T> data(T value){
        this.setData(value);
        return this;
    }


}

