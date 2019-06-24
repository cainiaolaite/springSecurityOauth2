package com.hua.dubbo.user.entity;

import java.io.Serializable;

/**
 * (Permission)实体类
 *
 * @author makejava
 * @since 2019-05-16 10:11:47
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = -52655402137257749L;
    //权限ID
    private String id;
    //权限名称
    private String name;
    //链接
    private String url;
    //类型
    private String type;
    //描述
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}