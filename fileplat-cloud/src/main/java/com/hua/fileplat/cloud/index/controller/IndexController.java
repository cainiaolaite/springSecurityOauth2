package com.hua.fileplat.cloud.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * /cloud/index/index.jsp
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "cloud/index/index";
    }
}
