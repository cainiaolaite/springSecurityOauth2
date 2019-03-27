package com.hua.fileplat.cloud.login.controller;

import com.hua.base.entity.Result;
import com.hua.fileplat.cloud.user.service.UserService;
import com.hua.fileplat.cloud.user.vo.LoginUserVo;
import com.hua.fileplat.cloud.user.vo.SessionUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.jdbc.JdbcOperationsSessionRepository;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录
 */
@Controller
public class LoginController {
    Logger logger= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    /*@Autowired
    private HttpSession session;*/
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private HttpSessionStrategy httpSessionStrategy;

    @Autowired
    private SessionRepository sessionRepository;

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
                        BindingResult bindingResult){
        Result result=userService.login(loginUserVo,bindingResult);
        if(result.isResult()){
            logger.info("sessionId {} 登錄成功后設置會話",httpSession.getId());
            Session session=sessionRepository.getSession(httpSession.getId());
            session.setAttribute(SessionUserVo.SESSION_USER_VO,result.getData());
            sessionRepository.save(session);
            result.setData(null);
        }
        return result;
    }

    /**
     * 退出
     * @param model
     * @return
     */
    @RequestMapping("/exit")
    public String exit(Model model){
        //httpSessionStrategy.onInvalidateSession(httpServletRequest,httpServletResponse);
        httpSession.invalidate();
        return "cloud/login/login";
    }

    /**
     * 到达管理页面
     * @return
     */
    @RequestMapping("/to_manager")
    public String toManager(@SessionAttribute(SessionUserVo.SESSION_USER_VO) SessionUserVo sessionUserVo){
        logger.info("session 用户 "+sessionUserVo.toString());
        return "cloud/manager/index";
    }



}
