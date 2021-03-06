package com.yjxxt.server;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yjxxt.server.mapper")
public class YebServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(YebServerApplication.class,args);
    }
}
