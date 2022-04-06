package com.hqm.rabbit.utils.test.基础.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class 反射 {
    public static void main(String[] args) {
        try {
            Class clz = Class.forName("com.hqm.rabbit.utils.test.Data");
            // 获取public构造器数组
            Constructor[] cons = clz.getConstructors();
            for(int i=0;i<cons.length;i++){
                System.out.println(cons[i].getName());
                System.out.println(cons[i].getModifiers());
            }
            Method[] methods = clz.getMethods();
            for(int i=0;i<methods.length;i++){
                //System.out.println(clz.getDeclaredMethod(methods[i].getName()));
            }

        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
