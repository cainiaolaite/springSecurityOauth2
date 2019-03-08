package com.hua.fileplat.cloud.error.controller;

import com.hua.fileplat.cloud.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * /cloud/index/index.jsp
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @Autowired
    public RoleService roleService;

    @RequestMapping("/404")
    public String error404(Model model){
        return "cloud/error/404";
    }
}
