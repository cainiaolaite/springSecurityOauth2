package com.hua.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.github.tobato.fastdfs","com.hua.cloud.controller"})
public class FastDFSApplication {
  public static void main(String[] args) {
    SpringApplication.run(FastDFSApplication.class, args);
  }
}
