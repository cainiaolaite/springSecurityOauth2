package com.hua.cloud;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableEurekaClient//启动eureka 客户端
@EnableDiscoveryClient//启动 发现（Discovery）客户端
@EnableFeignClients(basePackages={"com.hua.cloud.service"})//open feign 客户端启动
@Configuration
public class SpringCloudRibbonConsumerApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringCloudRibbonConsumerApplication.class, args);
  }


  /**
   * Ribbon 负载均衡算法 IRule
   * RoundRobinRule  轮询策略
   * BestAvailableRule  选择并发量最小
   * RandomRule  随机策略
   * WeightedResponseTimeRule 权重响应
   * RetryRule  先按照 RoundRobinRule 策略获取 provider，若获取失败，则在指定的时限内重试。默认的时限为 500 毫秒。
   * @return
   */
  @Bean
  public IRule ribbonIRule(){
    return new RoundRobinRule();
  }

  /**
   * 自定义 负载均衡算法
   */
  /*public IRule ribbonIRule(){
    return new CustomRule();
  }*/
}
