package com.hua.fileplat.cloud.user.entity;

import java.io.Serializable;

/**
 * (RolePermission)实体类
 *
 * @author makejava
 * @since 2019-03-10 15:47:21
 */
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -86371983905478583L;
    
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