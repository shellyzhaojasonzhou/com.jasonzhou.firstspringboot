package com.jasonzhou.firstspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication //Spring Boot核心注解，用于开启自动配置
@Controller
public class FirstspringbootApplication {

	
    public static void main(String[] args) {
        SpringApplication.run(FirstspringbootApplication.class, args);
    }
}
