package com.hua.cloud.service;

import com.hua.cloud.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient("spring-cloud-provider")//feignClient feign的客户端
@RequestMapping("user")//符合 restful 协议
public interface UserService {

  /**
   *
   * @param id
   * @return
   */
  @GetMapping("selectOne")//符合 restful 协议
  User selectOne(@RequestParam String id);

}
