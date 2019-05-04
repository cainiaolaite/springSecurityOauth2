package com.hua.primary.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 结合属性
 * @author
 */
@Component
@PropertySource(value = "classpath:application-auto-pro.properties",encoding = "utf-8")
@ConfigurationProperties(
        value="list"
)
@Data
public class ListPro {
    private List<String> authorList;//开发者
}
