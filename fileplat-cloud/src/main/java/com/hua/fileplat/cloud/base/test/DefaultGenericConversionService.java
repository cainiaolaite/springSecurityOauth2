package com.hua.fileplat.cloud.base.test;

import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;

public class DefaultGenericConversionService extends GenericConversionService {
    public DefaultGenericConversionService(){
        this.addConverter(Object.class, byte[].class,
                new SerializingConverter());
        this.addConverter(byte[].class, Object.class,
                new DeserializingConverter());
    }
}
