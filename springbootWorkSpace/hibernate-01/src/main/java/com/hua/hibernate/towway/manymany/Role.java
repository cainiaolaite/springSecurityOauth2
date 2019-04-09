package com.hua.hibernate.towway.manymany;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 * 一个 角色 可以有多个权限
 */
public class Role {
    private int id;
    private String name;
    /**
     * 默认添加一个空集合，以防查询的时候没有 部门就会容易报错
     */
    private Set<Permission> permissionSet=new HashSet<Permission>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }
}
