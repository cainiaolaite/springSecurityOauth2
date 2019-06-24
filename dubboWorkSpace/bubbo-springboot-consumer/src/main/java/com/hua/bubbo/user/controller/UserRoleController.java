package com.hua.bubbo.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hua.dubbo.user.entity.UserRole;
import com.hua.dubbo.user.service.UserRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * (UserRole)表控制层
 *
 * @author makejava
 * @since 2019-05-16 09:59:56
 */
@RestController
@RequestMapping("userRole")
public class UserRoleController {
    /**
     * 服务对象
     */
    @Reference
    private UserRoleService userRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserRole selectOne(String id) {
        return this.userRoleService.queryById(id);
    }

}