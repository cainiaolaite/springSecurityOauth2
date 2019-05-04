package com.hua.primary.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 对象属性
 * @author
 */
@Component
@PropertySource(value = "classpath:application-auto-pro.properties",encoding = "utf-8")
@ConfigurationProperties(
        value="authors"
)
@Data
public class ListObjectPro {
    private List<Author> authorList;//开发者
}
