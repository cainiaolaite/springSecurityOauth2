package com.hua.fileplat.cloud.base.aop.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void add(){
        System.out.println("UserService add()");
    }
    @Override
    public boolean delete(){
        System.out.println("UserService delete()");
        return false;
    }
    @Override
    public void edit(){
        System.out.println("UserService edit()");
        int i = 5/0;
    }
}