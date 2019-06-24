package com.hua.dubbo;

import com.hua.dubbo.entity.User;
import com.hua.dubbo.service.DubboBaseService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerTest {
    public static void main(String[] args){
        //启动spring
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring-dubbo-config.xml");
        applicationContext.start();
        //获取bean  方法级别的缓存测试
        DubboBaseService dubboBaseService= (DubboBaseService) applicationContext.getBean("dubboBaseService");
        for(int i=0;i<1000;i++){
            User user=dubboBaseService.sreachUser("wuhaihua"+i);
            System.out.println(user.toString());
        }
        User user=dubboBaseService.sreachUser("demo");
        dubboBaseService.sreachUser("wuhaihua20");
    }
}
