package com.pan.auctionsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.pan.auctionsystem.util.domin")
@EnableCaching
public class ActionsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionsystemApplication.class, args);
    }

}
