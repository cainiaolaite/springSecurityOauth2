package com.hua.primary.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("pro")
public class ProMsgService implements MsgService {
    @Override
    public String message() {
        return "我是生产环境";
    }
}
