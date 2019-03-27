package com.hua.primary.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevMsgService implements MsgService {
    @Override
    public String message() {
        return "我是开发环境";
    }
}
