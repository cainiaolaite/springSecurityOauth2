package com.hua.primary.controller;

import com.hua.primary.entity.User;
import com.hua.primary.service.UserService;
import com.hua.primary.vo.UserPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 用户分页查询
     * @param userPageVo
     * @param pageable
     * @return
     */
    @GetMapping("/page")
    @ResponseBody
    public Page<User> userPage(UserPageVo userPageVo, @PageableDefault(size = 2, sort = {"createTime"}, direction = Sort.Direction.DESC) Pageable pageable){
        return userService.userPage(userPageVo,pageable);
    }

    /**
     * 查询user
     * @param user
     * @return
     */
    @GetMapping("/get")
    @ResponseBody
    public User getUser(User user){
        return userService.getUser(user);
    }

    /**
     * 用户保存
     * @return
     */
    @GetMapping("/save")
    @ResponseBody
    public User saveUser(){
        return userService.saveUser(null);
    }
}
