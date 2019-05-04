package com.hua.hibernate.towway.manyone;

import java.util.HashSet;
import java.util.Set;

/**
 * 公司
 */
public class Company {

    private int id;
    private String name;
    /**
     * 默认添加一个空集合，以防查询的时候没有 部门就会容易报错
     */
    private Set<Department> departmentSet=new HashSet<Department>();

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

    public Set<Department> getDepartmentSet() {
        return departmentSet;
    }

    public void setDepartmentSet(Set<Department> departmentSet) {
        this.departmentSet = departmentSet;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", departmentSet=" + departmentSet +
                '}';
    }
}
