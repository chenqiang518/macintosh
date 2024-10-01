//
//package com.vernon.poppy.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//
//
//
////表明这是一个配置类
//@Configuration
//public class JdbcConfiguration {
//
//    @Value("${jdbc.driverClassName}")
//    private String driverClassName;
//    @Value("${jdbc.url}")
//    private String url;
//    @Value("${jdbc.username:newroot}")
//    private String username;
//    @Value("${jdbc.password}")
//    private String password;
//
//
//    @Bean
//    public HashMap<String,String> getJDBC(){
//
//        HashMap<String, String> hashMap = new HashMap<String,String>() {
//            {
//                put("driverClassName", driverClassName);
//                put("url", url);
//                put("username", username);
//                put("password", password);
//
//            }
//        };
//        return hashMap;
//    }
//
//
//
//}
