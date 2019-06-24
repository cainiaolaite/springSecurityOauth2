package com.hua.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
public class SpringCloudConsumerApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringCloudConsumerApplication.class, args);
  }

  /**
   *  这是客户端请求模板  符合  HTTP RestFul 协议
   *  只用于 HTTP RestFul 协议的 请求类
   * @return
   */
  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }
}
