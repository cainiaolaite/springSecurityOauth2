package com.hua.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

//启动反应式MongoDB数据仓库 （Repositories 仓库）
@EnableReactiveMongoRepositories
@SpringBootApplication
public class WebfluxSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxSpringbootApplication.class, args);
    }

}
