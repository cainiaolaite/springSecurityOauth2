package com.hua.dubbo.user.entity;

import java.io.Serializable;

/**
 * (RolePermission)实体类
 *
 * @author makejava
 * @since 2019-05-16 10:13:06
 */
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 873749192290073306L;
    
    private String id;
    //权限ID
    private String permissionId;
    //角色ID
    private String roleId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}