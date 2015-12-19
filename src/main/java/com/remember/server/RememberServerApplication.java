package com.remember.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

//@EnableAutoConfiguration
@SpringBootApplication
@EnableWebMvc
//@ComponentScan(basePackages = "com.remember.server.*")
public class RememberServerApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

    public static void main(String[] args) {
        SpringApplication.run(RememberServerApplication.class, args);
    }

}
