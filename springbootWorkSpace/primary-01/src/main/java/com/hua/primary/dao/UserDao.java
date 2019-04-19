package com.hua.primary.dao;

import com.hua.primary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 用户持久层接口
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User> {



}
