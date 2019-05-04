package com.hua.primary.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 属性
 * @author
 */
@Component
@PropertySource(value = "classpath:application-auto-pro.properties",encoding = "utf-8")
@Data
public class Pro {
    @Value("${pro.author}")
    private String author;//开发者
}
