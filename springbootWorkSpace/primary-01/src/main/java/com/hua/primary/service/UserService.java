package com.hua.primary.service;

import com.hua.primary.entity.User;
import com.hua.primary.vo.UserPageVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 用户服务层接口
 */
public interface UserService {
    /**
     * 用户分页查询
     * @param userPageVo
     * @param pageable
     * @return
     */
    Page<User> userPage(UserPageVo userPageVo,Pageable pageable);

    /**
     * 保存用户
     * @param user
     * @return
     */
    User saveUser(User user);

    /**
     * 查询用户
     * @param user
     * @return
     */
    User getUser(User user);
}
