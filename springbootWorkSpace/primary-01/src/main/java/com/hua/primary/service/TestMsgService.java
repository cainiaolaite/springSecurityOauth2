package com.hua.primary.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class TestMsgService implements MsgService {
    @Override
    public String message() {
        return "我是测试环境";
    }
}
