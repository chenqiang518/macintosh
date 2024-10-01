package com.vernon.poppy.controller;


import com.vernon.poppy.exception.ServiceException;
import com.vernon.poppy.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

//3、RestController注解告诉该类与前端页面交互
@RestController
//2、创建交互的类  BaseGetController
@RequestMapping("/dev")
@Api(tags = "基本Get请求处理")
public class BaseGetController {
    //    http://localhost:8080/dev/first
    //5、声明请求方式及其路径pos
//    @GetMapping("/first")
    //path,value 对应的内容一致，都是请求路径
    @RequestMapping(path = "/first",method = RequestMethod.GET)
    //4、请求方法
    @ApiOperation(value = "getFirst请求")
    R getFirst(){
//        int a = 10 /0;
        throw new ServiceException("get请求有问题",30000);

//        return "Hello SpringBoot!";
    }

    //http://localhost:8080/topic/{id}   URI
    //对应浏览器页面显示：
    @GetMapping("/topic/{id}")
//String getTopic(@PathVariable("id") String tid){
    @ApiOperation("URI路径参数")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "帖子地址ID")
    )
    String getTopic(@PathVariable(name = "id",value = "id") String id){

        return "这是一个帖子地址为："+ id +"的内容!";
    }

    //http://localhost:8080/native?sid={sid}
    //这是一个本国的地址为：{sid} 的内容!
    //queryParam
    @GetMapping(path = "/native",params = {
            "id=66",
            "sid=ifodua",
            "desc=dsfasdfasd"
    })
//    String getNative(@RequestParam("sid") String id){
//    String getNative(@RequestParam String sid){
    //http://localhost:8080/native?id={id}
    String getNative1(@RequestParam String id){

        return "这是一个本国的地址为："+id+" 的内容!getNative1";
    }

    //queryParam
    @GetMapping("/native")
//    String getNative(@RequestParam("sid") String id){
//    String getNative(@RequestParam String sid){
    //http://localhost:8080/native?id={id}
    String getNative2(@RequestParam String id){

        return "这是一个本国的地址为："+id+" 的内容!";
    }



}

