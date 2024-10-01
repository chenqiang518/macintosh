package com.vernon.poppy.core;

import com.vernon.poppy.exception.ServiceException;
import com.vernon.poppy.util.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

//ControllerAdvice  AOP面向切面编程，对原来对功能没有侵入性，原来什么样子现在还是什么样子
//我只是在原来的功能的基础上给你扩展出一个功能，统一异常处理功能
//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    String tips = "系统繁忙，请稍后重试";

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
//    @ResponseBody
    public R exceptionHandler(Exception e){
        //记录日志
        //通知运维
        //通知开发
        //控制台打印异常，万一出现异常调试
        e.printStackTrace();
        return R.error().message("非业务异常 "+ tips);

    }


    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R servceExceptionHandler(HttpServletRequest req, ServiceException se){
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        se.printStackTrace();
        return R.error().message("业务异常 "+ tips +" RestAPI:"+method + " "+ requestUrl).code(se.getCode());
    }



    ////    .ArithmeticException
    @ExceptionHandler({ArithmeticException.class})
//    @ResponseBody
    public R exceptionHandler1(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("当前人数过多，请稍后再试");
    }

}

