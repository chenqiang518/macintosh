package com.vernon.poppy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

//启动类
//默认一般创建为：artifactId+Application
//类名必须以Application结尾
// @SpringBootApplication=@Configuration  + @EnableAutoConfiguration + @ComponentScan
@SpringBootApplication
@MapperScan("com.vernon.poppy.dao")
public class PoppyApplication {
    public static void main(String[] args) {
        SpringApplication.run(PoppyApplication.class);
    }
}