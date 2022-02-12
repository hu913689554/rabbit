package com.hqm.rabbit.utils.jsonparam;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestSingleParam {
    String value();

    boolean required() default true;

    String defaultValue() default "";
}