package com.hua.webflux.entity;


import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

//mongoDB 表标识 制定在MongoDB 中生成表
@Document(collection="student")
public class Student {
    @Id
    private String id;//MongoDB中的主见一般为String 类型

    @NotNull(message = "名称不能为空")
    private String name;

    //range 范围
    @Range(min = 10,max = 50,message = "年龄不能小于{min}岁，不能大于{max}岁")
    private Integer age;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
