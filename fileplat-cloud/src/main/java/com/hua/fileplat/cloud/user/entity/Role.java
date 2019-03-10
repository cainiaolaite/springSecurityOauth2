package com.hua.fileplat.cloud.user.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2019-03-10 15:47:21
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 306871642571427991L;
    //id
    private String id;
    //姓名
    private String name;
    //更新时间
    private Date updateTime;
    //创建时间
    private Date createTime;


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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}