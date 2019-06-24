package com.hua.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages={"com.hua.cloud.dao"})
public class SpringCloudProviderApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudProviderApplication.class, args);
  }


}
