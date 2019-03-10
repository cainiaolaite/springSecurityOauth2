package com.hua.fileplat.cloud.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(Model model){
        return "cloud/index/index";
    }

    @RequestMapping("/sendMessage")
    public String sendMessage(Model model){
        return "cloud/index/index";
    }
}
