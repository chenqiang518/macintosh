package com.vernon.poppy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/t")
///t/config.properties/e/config.properties
public class GetController {

    //    配置文件对应的key的内容
    @Value("${android.deviceName}")
    private String deviceName;
    //总结：1、需要用${} 把自定义的key包裹起来；2、${...}主要用于加载外部属性文件中的值


    @Value("${android.app:wechat}")
    private String app;
    //http://localhost:8080/t/topic/{id}?sid={sidValue}
//    http://localhost:8080/t/topic/{id}?sid=    http://localhost:8080/t/topic/{id}?sid=66
    //defaultValue声明后，对应的required参数值为false
//    @GetMapping("/t/topic/{did}")
    @GetMapping(value = {"/topic/{did}/u","/topic/u"})  ////    @RequestMapping(method = RequestMethod.GET)
    String getURIWithParam(@PathVariable(value = "did",required = false) String topid, @RequestParam(defaultValue="66") int sid){
        System.out.println("配置文件值："+deviceName);
        System.out.println("配置文件app值："+app);

        return "这是一个帖子地址为："+topid+" 并且参数sid为"+sid+"的内容!";

    }

    //http://localhost:8080/t/top/{city}/{year}?describe=&money=
    //{year}年{city}人均支出为：45675元!
    //对应浏览器页面显示：{year}年{city}人均{describe}为：{money}元!
    @GetMapping("/top/{city}/{year}")
//    @GetMapping("/t/top/{city}/{year}")
    String getURIWithParams(@PathVariable int year,@PathVariable String city,
                            @RequestParam(required = false) String describe,
                            @RequestParam(defaultValue = "45675") int money){
        return "{"+year+"}年{"+city+"}人均{"+describe+"}为：{"+money+"}元!";
    }



}

