package com.hua.fileplat.cloud.index.controller;

import com.hua.fileplat.cloud.base.cookie.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
@Controller
public class IndexController {
    @Autowired
    private CookieService cookieService;
    @RequestMapping("/")
    public String index(Model model, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){
        cookieService.addCookie(httpServletRequest,httpServletResponse);
        return "cloud/index/index";
    }

    @RequestMapping("/sendMessage")
    public String sendMessage(Model model){
        return "cloud/index/index";
    }
}
