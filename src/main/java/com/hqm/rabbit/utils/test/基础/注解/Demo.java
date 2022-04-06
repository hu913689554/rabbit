package com.hqm.rabbit.utils.test.基础.注解;

@MyAnnotation(getValue = "这是一个测试注解类")
public class Demo {
    @MyAnnotation(getValue = "这是一个测试注解属性")
    public String name;
    @MyAnnotation(getValue = "这是一个测试注解方法")
    public void hello(){
    }
    @MyAnnotation()
    public void defaultMethod(){
    }
}
