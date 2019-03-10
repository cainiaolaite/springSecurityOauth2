package com.hua.fileplat.cloud.error.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
@RequestMapping("/error")
public class ErrorController {


    @RequestMapping("/404")
    public String error404(Model model){
        return "cloud/error/404";
    }
}
