package com.hua.dubbo.service;

import com.hua.dubbo.entity.User;

/**
 * 服务的第二种实现
 * 版本2
 */
public class DubboBaseServiceChangeImpl implements DubboBaseService {
    @Override
    public User sreachUser(String name) {
        System.out.println("shi xian cha xun yong hu："+name);
        User user=new User();
        user.setName(name);
        return user;
    }
}
