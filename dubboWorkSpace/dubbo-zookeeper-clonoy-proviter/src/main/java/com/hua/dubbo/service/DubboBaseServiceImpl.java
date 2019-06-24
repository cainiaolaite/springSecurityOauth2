package com.hua.dubbo.service;


import com.hua.dubbo.entity.User;

/**
 * 服务实现
 */
public class DubboBaseServiceImpl implements DubboBaseService {

    /**
     * 根据用户名查询用户
     * @param name
     * @return
     */
    @Override
    public User sreachUser(String name) {
        System.out.println("查询用户："+name);
        User user=new User();
        user.setName(name);
        return user;
    }
}
