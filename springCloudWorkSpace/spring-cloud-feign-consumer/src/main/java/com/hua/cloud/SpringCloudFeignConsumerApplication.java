package com.hua.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient//启动eureka 客户端
@EnableDiscoveryClient//启动 发现（Discovery）客户端
@EnableFeignClients(basePackages={"com.hua.cloud.service"})//open feign 客户端启动
@Configuration
public class SpringCloudFeignConsumerApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringCloudFeignConsumerApplication.class, args);
  }

}
