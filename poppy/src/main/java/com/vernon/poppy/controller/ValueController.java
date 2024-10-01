//
//package com.vernon.poppy.controller;
//
//import com.vernon.poppy.config.JdbcConfiguration;
//import com.vernon.poppy.service.JdService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//
//
//@RestController
//public class ValueController {
//
//    @Autowired
//    JdService jdService;
//
//
//    @GetMapping(value = "/getJdbc",produces = "application/json")
//    HashMap<String,String> getJdbc(){
//        return jdService.getJdbc();
//    }
//}
//
