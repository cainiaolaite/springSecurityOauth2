package com.hua.bubbo.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hua.dubbo.user.entity.RolePermission;
import com.hua.dubbo.user.service.RolePermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (RolePermission)表控制层
 *
 * @author makejava
 * @since 2019-05-16 09:59:53
 */
@RestController
@RequestMapping("rolePermission")
public class RolePermissionController {
    /**
     * 服务对象
     */
    @Reference
    private RolePermissionService rolePermissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public RolePermission selectOne(String id) {
        return this.rolePermissionService.queryById(id);
    }

}