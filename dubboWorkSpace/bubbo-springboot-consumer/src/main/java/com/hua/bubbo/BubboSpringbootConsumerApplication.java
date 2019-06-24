package com.hua.bubbo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubboConfiguration
@SpringBootApplication
public class BubboSpringbootConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BubboSpringbootConsumerApplication.class, args);
    }

}
