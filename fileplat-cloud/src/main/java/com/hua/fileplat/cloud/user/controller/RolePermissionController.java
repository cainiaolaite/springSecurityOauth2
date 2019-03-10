package com.hua.fileplat.cloud.user.controller;

import com.hua.fileplat.cloud.user.entity.RolePermission;
import com.hua.fileplat.cloud.user.service.RolePermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (RolePermission)表控制层
 *
 * @author makejava
 * @since 2019-03-10 15:47:21
 */
@RestController
@RequestMapping("rolePermission")
public class RolePermissionController {
    /**
     * 服务对象
     */
    @Resource
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