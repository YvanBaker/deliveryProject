package com.delivery.annotaions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * @author Yvan
 * @Description 管理员角色
 * @Classname Admin
 * @Date 2020/11/3 9:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, METHOD})
public @interface Admin {
}
