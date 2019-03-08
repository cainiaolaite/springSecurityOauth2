package com.hua.fileplat.cloud.base.exception;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.BindException;

public class SpringHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        return handlerException(httpServletRequest,e);
    }

    public ModelAndView handlerException(HttpServletRequest request,Exception e){
        if(e instanceof BindException){
            //400
        }else if(e instanceof ConversionNotSupportedException){
            //500
        }else if(e instanceof HttpMediaTypeNotAcceptableException){
            //406 不可接受
        }else if(e instanceof HttpMediaTypeNotSupportedException){
            //415 不支持的媒体类型
        }else if(e instanceof NoHandlerFoundException){
            //404
            return new ModelAndView("/error/404");
        }else if(e instanceof NoSuchRequestHandlingMethodException){
            //404
            return new ModelAndView("/error/404");
        }
        return null;
    }

    /**
     * 判断是否ajax请求
     * @param request 请求对象
     * @return true:ajax请求  false:非ajax请求
     */
    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

}
