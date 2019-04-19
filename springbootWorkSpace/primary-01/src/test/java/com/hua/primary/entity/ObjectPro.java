package com.hua.primary.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 对象属性
 * @author
 */
@Component
@PropertySource(value = "classpath:application-auto-pro.properties",encoding = "utf-8")
@ConfigurationProperties(
        value="object"
)
@Data
public class ObjectPro {
    private String author;//开发者
}
