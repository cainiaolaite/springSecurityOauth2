package com.hua.base.socket.encode;

import com.alibaba.fastjson.JSON;
import com.hua.base.entity.Result;
import com.hua.base.socket.decode.ResultDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Result编码器
 */
public class ResultEncode implements Encoder.Text<Result>{
    Logger logger= LoggerFactory.getLogger(ResultDecoder.class);
    /**
     *
     * @param result
     * @return
     * @throws EncodeException
     */
    @Override
    public String encode(Result result) throws EncodeException {
        String resutlContent=JSON.toJSONString(result);
        logger.trace("encode content:"+resutlContent);
        return resutlContent;
    }

    /**
     * 初始化
     * @param endpointConfig
     */
    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {
    }
}
