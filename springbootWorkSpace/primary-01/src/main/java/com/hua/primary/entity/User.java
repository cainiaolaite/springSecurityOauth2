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
@Table(name = "dev_user")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class User implements Serializable {
    @Id
    @GenericGenerator(name="uuid", strategy="uuid")
    @GeneratedValue(generator="uuid")
    private String id;
    /**
     * 用户名
     */
    @Column(name="user_name",length = 50,unique=true,nullable = false)
    private String userName;
    /**
     * 密码
     */
    @Column(name="password",length = 50,nullable = false)
    private String password;
    /**
     * 手机号
     */
    @Column(name="phone",length = 11,unique=true)
    private String phone;
    /**
     * 邮箱
     */
    @Column(name="email",length = 50,unique=true)
    private String email;
    /**
     * 密码salt
     */
    @Column(name="salt",length = 32,nullable = false)
    private String salt;
    /**
     * 用户类型
     */
    @Column(name="type",length = 3,nullable = false)
    private Integer type;
    /**
     * 用户状态
     */
    @Column(name="status",length = 3,nullable = false)
    private Integer status;
    /**
     * 用户描述
     */
    @Column(name="description",length = 500)
    private String description;
    /**
     * 创建时间
     */
    @Column(name="create_time",nullable = false)
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

    /**
     * 公司id
     */
    @ManyToOne(targetEntity=Company.class,fetch=FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
