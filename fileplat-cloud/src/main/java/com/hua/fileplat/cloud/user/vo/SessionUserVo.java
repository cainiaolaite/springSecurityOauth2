package com.hua.fileplat.cloud.user.vo;

import com.hua.fileplat.cloud.user.entity.User;

import java.io.Serializable;
import java.util.Date;

/**
 * 会话用户Vo
 */
public class SessionUserVo  implements Serializable {
    public static final String SESSION_USER_VO="SESSION_USER_VO";
    private String id;//用户ID
    private String userName;//用户名
    private String  email;//用户邮箱
    private String  phone;//手机号
    private String  password;//用户密码
    private String  address;//用户地址
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    
    public SessionUserVo(){}
    public SessionUserVo(User user){
        this.id=user.getId();//用户ID
        this.userName=user.getUserName();//用户名
        this.email=user.getEmail();//用户邮箱
        this.phone=user.getPhone();//手机号
        this.password=user.getPassword();//用户密码
        this.address=user.getAddress();//用户地址
        this.createTime=user.getCreateTime();//创建时间
        this.updateTime=user.getUpdateTime();//更新时间
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SessionUserVo{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
