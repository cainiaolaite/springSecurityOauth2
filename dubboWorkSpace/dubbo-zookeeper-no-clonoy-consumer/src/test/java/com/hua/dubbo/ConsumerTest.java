package com.hua.dubbo;

import com.hua.dubbo.entity.User;
import com.hua.dubbo.service.DubboBaseService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerTest {
    public static void main(String[] args){
        //启动spring
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring-dubbo-config.xml");
        applicationContext.start();
        //获取bean
        DubboBaseService dubboBaseService= (DubboBaseService) applicationContext.getBean("dubboBaseService");
        User user=dubboBaseService.sreachUser("wuhaihua");
        System.out.println(user.toString());
    }
}
