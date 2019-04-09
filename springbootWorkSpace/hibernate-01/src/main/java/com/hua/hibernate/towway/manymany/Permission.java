package com.hua.hibernate.towway.manymany;

import java.util.HashSet;
import java.util.Set;

/**
 * 权限
 * 一个权限可以 被多个角色所拥有
 */
public class Permission {
    private int id;
    private String name;
    /**
     * 默认添加一个空集合，以防查询的时候没有 部门就会容易报错
     */
    private Set<Role> roleSet=new HashSet<Role>();
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
    public Set<Role> getRoleSet() {
        return roleSet;
    }
    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
