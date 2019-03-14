package com.pan.auctionsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ActionsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionsystemApplication.class, args);
    }

}
