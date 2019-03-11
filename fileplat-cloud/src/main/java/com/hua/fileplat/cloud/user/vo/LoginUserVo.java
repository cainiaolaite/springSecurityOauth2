package com.hua.fileplat.cloud.user.vo;

import com.hua.fileplat.cloud.base.intl.IntlMessage;
import com.hua.fileplat.cloud.user.entity.User;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;


/**
 * 用户VO
 */
public class LoginUserVo {
    @Length(min=6,max = 16,message="{field.user.username.length}")
    private String userName;//用户名
    @Email(message = "{field.user.email.error}")
    @Length(min=6,max = 16,message="{field.user.email.length}")
    private String email;//邮箱
    @Pattern(regexp="^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\\\D])|(18[0,5-9]))\\\\d{8}$",message = "{field.user.phone.error}")
    @Length(min=6,max = 16,message="{field.user.phone.length}")
    private String phone;//电话
    @NotEmpty(message = "{field.user.password.null}")
    @Length(min=6,max = 16,message="{field.user.password.length}")
    private String password;//密码

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

    /**
     * 转换为User
     * @return
     */
    public User toUser(){
        User user=new User();
        user.setUserName(this.getUserName());
        user.setPassword(this.getPassword());
        user.setPhone(this.getPhone());
        user.setEmail(this.getEmail());
        return user;
    }
}
