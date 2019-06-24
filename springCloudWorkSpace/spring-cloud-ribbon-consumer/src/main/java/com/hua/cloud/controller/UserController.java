package com.hua.cloud.controller;

import com.hua.cloud.entity.User;
import com.hua.cloud.service.UserService;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@RestController
@RequestMapping("/feign/user")
public class UserController {


  @Autowired
  private UserService userService;


  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("selectOne")
  public User selectOne(@RequestParam  String id) {
    return userService.selectOne(id);
  }

  /**
   * 发现客户端
   */
  @GetMapping("discovery")
  public Set<String> discoveryClient(){
    Set<String> regions=null;//discoveryClient.getAllKnownRegions();
    return regions;
  }
}
