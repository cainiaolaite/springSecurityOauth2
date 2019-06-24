package com.hua.primary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring boot RestController 是用于放回数据，不能返回页面
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
      return "/index.jsp";
    }
}
