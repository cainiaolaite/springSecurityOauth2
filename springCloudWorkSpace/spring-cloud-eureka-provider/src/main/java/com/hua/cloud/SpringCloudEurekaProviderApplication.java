package com.hua.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableEurekaClient
@MapperScan(basePackages={"com.hua.cloud.dao"})
public class SpringCloudEurekaProviderApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudEurekaProviderApplication.class, args);
  }


}
