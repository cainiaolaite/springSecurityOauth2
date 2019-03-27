package com.hua.fileplat.cloud.base.cookie;

import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie 服务
 */
@Service
public class CookieService {
    public static final String COOKIE_NAME= "SESSION";
    private static Logger logger= LoggerFactory.getLogger(CookieService.class);
    /**
     * 添加Cookie
     * @param httpServletRequest
     * @param httpServletResponse
     */
    public void addCookie(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        Cookie[] cookieArray=httpServletRequest.getCookies();
        boolean addCookieFlag=false;
        if(cookieArray == null || cookieArray.length == 0){
            logger.trace("no cookie");
            addCookieFlag=true;
        }else{
            for(Cookie cookie:cookieArray){
                if(StringUtils.equals(cookie.getName(),COOKIE_NAME)){
                    addCookieFlag=false;
                    break;
                }else{
                    logger.trace("no cookie name is SESSION de cookie");
                    addCookieFlag=true;
                }
            }
        }

        if(addCookieFlag){
            String str= ObjectUtils.toString(System.currentTimeMillis());
            String md5=Md5Crypt.md5Crypt(str.getBytes());
            Cookie cookie=new Cookie(COOKIE_NAME, md5);
            httpServletResponse.addCookie(cookie);
        }
    }

}
