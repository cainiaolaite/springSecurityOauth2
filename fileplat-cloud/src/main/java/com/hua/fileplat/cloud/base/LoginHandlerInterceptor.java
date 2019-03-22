package com.hua.fileplat.cloud.base;

import com.hua.fileplat.cloud.base.cookie.CookieService;
import com.hua.fileplat.cloud.user.vo.SessionUserVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截
 */
public class LoginHandlerInterceptor  implements HandlerInterceptor {
    private static Logger logger= LoggerFactory.getLogger(LoginHandlerInterceptor.class);
    @Autowired
    private CookieService cookieService;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private HttpSessionStrategy httpSessionStrategy;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        cookieService.addCookie(httpServletRequest,httpServletResponse);
        String sessionId=httpSessionStrategy.getRequestedSessionId(httpServletRequest);
        if(StringUtils.isNotEmpty(sessionId)){
            Session session=sessionRepository.getSession(sessionId);
            if(session != null){
                SessionUserVo sessionUserVo=(SessionUserVo)session.getAttribute(SessionUserVo.SESSION_USER_VO);
                if(sessionUserVo != null){
                    return true;
                }
            }
        }
        logger.info("sessionId {} 沒有回話",sessionId);
        httpServletResponse.addHeader("sessionstatus", "timeOut");
        httpServletResponse.sendRedirect("/");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
