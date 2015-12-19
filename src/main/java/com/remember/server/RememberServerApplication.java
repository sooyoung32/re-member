package com.remember.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@EnableAutoConfiguration
@SpringBootApplication
//@ComponentScan(basePackages = "com.remember.server.*")
public class RememberServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RememberServerApplication.class, args);
    }
}
