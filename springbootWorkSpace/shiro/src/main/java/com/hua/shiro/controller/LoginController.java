package com.hua.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    private static Logger logger= LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录
     * @param request
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request,String username, String password){
        Subject subject= SecurityUtils.getSubject();
        if(StringUtils.isEmpty(username)){
            logger.error("username is not null");
            return null;
        }
        if(StringUtils.isEmpty(password)){
            logger.error("password is not null");
            return null;
        }
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken();
        usernamePasswordToken.setHost(request.getRemoteHost());
        usernamePasswordToken.setUsername(username);
        usernamePasswordToken.setPassword(password.toCharArray());
        try{
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException authenticationException){
            logger.error("authentication fail {}",authenticationException.getMessage());
            return null;
        }
        return "/view/shiro/index.html";
    }

}
