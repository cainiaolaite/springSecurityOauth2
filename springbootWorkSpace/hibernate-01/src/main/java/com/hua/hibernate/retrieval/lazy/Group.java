package com.hua.hibernate.retrieval.lazy;


import java.util.HashSet;
import java.util.Set;

/**
 * 单向多对多
 */
public class Group {
    private int id;
    private String name;
    /**
     * 默认添加一个空集合，以防查询的时候没有 部门就会容易报错
     */
    private Set<Member> memberSet=new HashSet<Member>();

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

    public Set<Member> getMemberSet() {
        return memberSet;
    }

    public void setMemberSet(Set<Member> memberSet) {
        this.memberSet = memberSet;
    }
}
