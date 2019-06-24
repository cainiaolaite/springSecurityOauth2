package com.hua.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient//启动eureka 客户端
@EnableDiscoveryClient//启动 发现（Discovery）客户端
@Configuration
public class SpringCloudEurekaConsumerApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringCloudEurekaConsumerApplication.class, args);
  }

  /**
   *  这是客户端请求模板  符合  HTTP RestFul 协议
   *  只用于 HTTP RestFul 协议的 请求类
   * @return
   */
  //@LoadBalanced 开启负载均衡
  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

}
