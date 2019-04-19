package com.hua.primary.controller;

import com.hua.primary.entity.ListObjectPro;
import com.hua.primary.entity.ListPro;
import com.hua.primary.entity.ObjectPro;
import com.hua.primary.entity.Pro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义属性集合
 */
@RestController
public class AutoPropertiesController {
    @Autowired
    private Pro pro;

    @Autowired
    private ObjectPro objectPro;

    @Autowired
    private ListPro listPro;

    @Autowired
    private ListObjectPro listObjectPro;
    /**
     * 访问属性
     * @return
     */
    @GetMapping("/visitPro")
    @ResponseBody
    public String visitPro(){
        return pro.toString();
    }

    /**
     * 访问对象属性
     * @return
     */
    @GetMapping("/visitProjectPro")
    @ResponseBody
    public String visitProjectPro(){
        return objectPro.toString();
    }

    /**
     * 访问集合属性
     * @return
     */
    @GetMapping("/visitListPro")
    @ResponseBody
    public String visitListPro(){
        return listPro.toString();
    }

    /**
     * 发文对象集合属性
     * @return
     */
    @GetMapping("/visitListProjectPro")
    @ResponseBody
    public String visitListProjectPro(){
        return listObjectPro.toString();
    }

}
