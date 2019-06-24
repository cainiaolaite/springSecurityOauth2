package com.hua.cloud.service;

import com.hua.cloud.entity.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HystrixFallbackUserSerivce implements FallbackFactory<UserService>{
  @Override
  public UserService create(Throwable throwable) {
    //调用服务提供者后出现的错误
    System.out.println(throwable.getMessage());
    //放回一个默认的服务，称为服务降级
    return (id)->{
      User user=new User();
      user.setUserName("类级别的方法调用");
      return user;
    };
  }

}
