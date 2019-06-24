package com.hua.bubbo.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hua.dubbo.user.entity.Permission;
import com.hua.dubbo.user.service.PermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Permission)表控制层
 *
 * @author makejava
 * @since 2019-03-10 15:46:08
 */
@RestController
@RequestMapping("permission")
public class PermissionController {
    /**
     * 服务对象
     */
    @Reference
    private PermissionService permissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Permission selectOne(String id) {
        return this.permissionService.queryById(id);
    }

}