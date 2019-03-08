package com.hua.base.socket.decode;

import com.alibaba.fastjson.JSON;
import com.hua.base.entity.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;

/**
 * Result编码器
 */
public class ResultDecoder implements Decoder.Text<Result>{
    Logger logger= LoggerFactory.getLogger(ResultDecoder.class);

    @Override
    public Result decode(String s) throws DecodeException {
        logger.trace("decode content:"+s);
        return JSON.parseObject(s,Result.class);
        /*if(StringUtils.isEmpty(s)){
            logger.debug("decode content is empty");
            return null;
        }else{
            Result result=JSON.parseObject(s,Result.class);
            if(result == null){
                logger.debug("decode content json to result is null");
                return null;
            }
            if(result.getData() == null){
                logger.info("result data is null");
                return result;
            }else{
                Class dataClass=getResultDataClass();
                if(dataClass == null){
                    logger.info("result data class is null");
                    return result;
                }else{
                    Object object=JSON.parseObject(result.getData().toString(),dataClass);
                    result.setData(object);
                    logger.trace("result data class is success");
                    return result;
                }
            }
        }*/
    }



    @Override
    public boolean willDecode(String s) {
        return false;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
