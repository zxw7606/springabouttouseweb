package com.annotation;

import java.lang.annotation.*;

/**
 * @description: 用户转化
 * @create: 2019-08-12 16:41
 * @author: zxw
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
public @interface UserFormat {

    ROLE role() default ROLE.TEACHER;

    enum ROLE {
        STUDENT,
        TEACHER;
    }
}
