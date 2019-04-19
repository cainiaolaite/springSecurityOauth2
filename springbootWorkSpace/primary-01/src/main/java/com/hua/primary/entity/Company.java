package com.hua.primary.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Cache;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户表
 * Cacheable 开启二级缓存
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "hibernateEhcache")
@Cacheable
@Entity
@Table(name = "dev_company")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Company implements Serializable {
    @Id
    @GenericGenerator(name="uuid", strategy="uuid")
    @GeneratedValue(generator="uuid")
    private String id;
    /**
     * 公司名称
     */
    @Column(name="name",length = 200,unique=true,nullable=false)
    private String name;
    /**
     * 公司类型
     */
    @Column(name="type",length = 3,nullable=false)
    private Integer type;
    /**
     * 公司状态
     */
    @Column(name="status",length = 3,nullable=false)
    private Integer status;
    /**
     * 公司描述描述
     */
    @Column(name="description",length = 500)
    private String description;
    /**
     * 创建时间
     */
    @Column(name="create_time",nullable=false)
    private Timestamp createTime;
    /**
     * 创建用户
     */
    @Column(name="create_user",length = 36)
    private String createUser;
    /**
     * 更新用户
     */
    @Column(name="update_user",length = 36)
    private String updateUser;
    /**
     * 更新时间
     */
    @Column(name="update_time")
    private Timestamp updateTime;

    /**
     * 版本
     */
    @Version
    @Column(name="version")
    private Integer version;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
