package com.delivery.annotaions;

/**
 * @author Yvan
 * @Description 用户token 拦截器
 * @Classname CustomerToken
 * @Date 2020/10/31 17:38
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CustomerToken {
}
