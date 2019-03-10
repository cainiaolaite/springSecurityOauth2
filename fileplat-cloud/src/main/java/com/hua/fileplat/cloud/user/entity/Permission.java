package com.hua.fileplat.cloud.user.entity;

import java.io.Serializable;

/**
 * (Permission)实体类
 *
 * @author makejava
 * @since 2019-03-10 15:46:08
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = -22952849104745140L;
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