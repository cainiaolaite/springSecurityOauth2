package com.hua.webflux.utils;

import com.hua.webflux.exception.StudentValidNameException;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * hibernate 验证 工具
 */
public class HibernateValidUtils {

    public static String[] NO_STUDENT_NAME={"admin","admin"};

    public static void validStudentName(String name)throws StudentValidNameException {
        //匹配用户名是否具有非法用户名
        //1.过滤掉
        Arrays.stream(NO_STUDENT_NAME)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return s.equalsIgnoreCase(name);
                    }
                })
                .findAny()
                .ifPresent(new Consumer(){
                    @Override
                    public void accept(Object o) {
                        throw new StudentValidNameException("使用非法用户名","student","name");
                    }
                });

    }
}
