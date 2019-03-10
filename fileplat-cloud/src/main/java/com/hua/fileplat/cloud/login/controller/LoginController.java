package com.hua.fileplat.cloud.login.controller;

import com.hua.base.entity.Result;
import com.hua.fileplat.cloud.user.service.UserService;
import com.hua.fileplat.cloud.user.vo.LoginUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 登录
 */
@Controller
public class LoginController {
    Logger logger= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;
    @RequestMapping("/to_login")
    public String index(Model model){
        return "cloud/login/login";
    }

    /**
     * 登录
     * @param model
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result login(Model model,
                        @Validated LoginUserVo loginUserVo,
                        BindingResult bindingResult,
                        Session session){
        Result result=userService.login(loginUserVo,bindingResult,session);
        return result;
    }

    /**
     * 退出
     * @param model
     * @return
     */
    @RequestMapping("/exit")
    public String exit(Model model, HttpSession session){
        session.invalidate();
        return "/";
    }



}
