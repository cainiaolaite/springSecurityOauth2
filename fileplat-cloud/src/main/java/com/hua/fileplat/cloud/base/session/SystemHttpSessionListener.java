package com.hua.fileplat.cloud.base.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 系统Session 监听
 */
public class SystemHttpSessionListener implements HttpSessionListener {

    Logger logger= LoggerFactory.getLogger(SystemHttpSessionListener.class);
    @Autowired
    private SessionRepository sessionRepository;


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        Session session=getSession(se);
        if(session != null){
            logger.info("有会话进入session id "+session.getId());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Session session=getSession(se);
        if(session != null){
            logger.info("有会话销毁session id "+session.getId());
        }
    }

    /**
     * 根据会话事件 取得 Spring Session
     * @param httpSessionEvent
     * @return
     */
    private Session getSession(HttpSessionEvent httpSessionEvent){
        HttpSession httpSession=httpSessionEvent.getSession();
        if(httpSession == null){
            logger.error("session 为空");
            return null;
        }
        Session session=sessionRepository.getSession(httpSession.getId());
        if(session == null){
            logger.error("不能通过HttpSession的session id {0} 取 Spring Session",httpSession.getId());
            return null;
        }
        return session;
    }
}
