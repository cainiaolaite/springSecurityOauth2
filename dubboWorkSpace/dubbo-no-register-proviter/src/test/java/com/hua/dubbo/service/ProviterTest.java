package com.hua.dubbo.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 测试
 */
public class ProviterTest {

    public static void main(String[] args) throws IOException {
        //启动spring
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring-dubbo-config.xml");
        applicationContext.start();
        System.out.println("服务提供者启动成功");
        //等待用户输入
        System.in.read();
    }
}
