package com.hqm.rabbit.utils.test.基础.注解;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String getValue() default "aaa";
}