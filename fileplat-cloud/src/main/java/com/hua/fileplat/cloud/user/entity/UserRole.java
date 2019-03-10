package com.hua.fileplat.cloud.user.entity;

import java.io.Serializable;

/**
 * (UserRole)实体类
 *
 * @author makejava
 * @since 2019-03-10 15:47:21
 */
public class UserRole implements Serializable {
    private static final long serialVersionUID = 808434871904058658L;
    //ID
    private String id;
    
    private String userId;
    //角色 ID
    private String roleId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}