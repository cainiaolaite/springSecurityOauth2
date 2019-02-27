package com.hua.fileplat.cloud.index.controller;

import com.hua.fileplat.cloud.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * /cloud/index/index.jsp
 */
@Controller
public class IndexController {

    @Autowired
    public RoleService roleService;

    @RequestMapping("/")
    public String index(Model model){
        /*try {
            roleService.transactionTest();
        }catch(Exception e){

        }
        roleService.selectRole();*/
        model.addAttribute("test","wuhaihua");
        return "cloud/index/index";
    }
}
