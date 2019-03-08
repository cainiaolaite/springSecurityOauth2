package com.hua.fileplat.cloud.user.socket;


import com.hua.base.entity.Result;
import com.hua.base.socket.decode.ResultDecoder;
import com.hua.base.socket.encode.ResultEncode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * webSocket
 */
@ServerEndpoint(
        value="/index/echo"/*,
        decoders=ResultDecoder.class,
        encoders= ResultEncode.class*/
        )
public class EchoEndpoint{
    public static Logger logger= LoggerFactory.getLogger(EchoEndpoint.class);
    public static Map<String,Session> sessionMap=new HashMap<String, Session>();
    /**
     * webSocket 生命周期方法  打开连接
     * @param session
     * @param endpointConfig
     */
    @OnOpen
    public void open(Session session, EndpointConfig endpointConfig){
        logger.info(session.getId()+"connection open");
        sessionMap.put(session.getId(),session);
    }

    /**
     * webSocket 生命周期方法 收到消息
     * @param session
     * @param message
     */
    @OnMessage
    public void message(Session session,String message){
        logger.info(session.getId()+"send message");
        //接收到消息后发送
        for(Session sessionItem:sessionMap.values()){//取得所有连接上这个接口的人，发送给所有人
            if(!StringUtils.equals(sessionItem.getId(),session.getId())){
                try {
                    logger.info(sessionItem.getId()+" send message :"+message);
                    sessionItem.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    logger.error(sessionItem.getId()+" send message fail:"+e.getMessage());
                    e.printStackTrace();
                }
            }
            logger.trace(" sessionItem id:"+sessionItem.getId()+" session id:"+session.getId());
        }
    }

    /**
     * webSocket 生命周期方法 关闭
     * @param session
     * @param reason
     */
    @OnClose
    public void close(Session session, CloseReason reason){
        logger.info(session.getId()+" close socket connection");
        sessionMap.remove(session.getId());
    }

    /**
     * webSocket 生命周期方法 错误
     * @param session
     * @param error
     */
    @OnError
    public void error(Session session,Throwable error){
        logger.info(session.getId()+" socket error :"+error.getMessage());
        sessionMap.remove(session.getId());
    }

}