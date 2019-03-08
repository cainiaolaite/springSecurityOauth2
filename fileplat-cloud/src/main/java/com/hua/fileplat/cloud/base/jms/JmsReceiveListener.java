package com.hua.fileplat.cloud.base.jms;

import com.hua.fileplat.cloud.user.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;

/**
 * JSM 接收消息侦听
 */
public class JmsReceiveListener implements SessionAwareMessageListener {

    @Autowired
    private RoleService roleService;
    /**
     * 日志
     */
    Logger logger= LoggerFactory.getLogger(JmsReceiveListener.class);
    @Override
    @Transactional
    public void onMessage(Message message, Session session) {
        try {
            logger.info("接收消息内容为："+message.getStringProperty("hello"));
            roleService.transactionTest1();
            int i=10/0;
        }catch (JMSException e){
            e.printStackTrace();
            logger.error("接收消息异常编号："+e.getErrorCode()+" 异常信息："+e.getMessage());
            try {
                session.rollback();
            } catch (JMSException e1) {
                logger.error(" 事务异常信息："+e.getMessage());
            }
        }

    }
}
