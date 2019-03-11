package com.hua.fileplat.cloud.base;

import com.hua.fileplat.cloud.user.vo.SessionUserVo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截
 */
public class LoginHandlerInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        SessionUserVo sessionUserVo=(SessionUserVo)httpServletRequest.getSession().getAttribute(SessionUserVo.SESSION_USER_VO);
        if(httpServletRequest.getUserPrincipal() != null){
            return true;
        }
        if(sessionUserVo == null){
            httpServletResponse.addHeader("sessionstatus", "timeOut");
            httpServletResponse.sendRedirect("/");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
