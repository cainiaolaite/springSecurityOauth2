package com.hua.dubbo.service;

import com.hua.dubbo.entity.User;

/**
 * dubbo 基础接口
 */
public interface DubboBaseService {
    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    User sreachUser(String name);
}
