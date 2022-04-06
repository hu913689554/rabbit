package com.hqm.rabbit.utils.test.基础;

public class 泛型 {
    public static void main(String[] args) {
        System.out.println(add(1,2));
    }
    //泛型中的类型在使用时指定，不需要强制类型转换（类型安全，编译器会检查类型）
    private static <T extends Number> double add(T a, T b) {
        System.out.println(a + "+" + b + "=" + (a.doubleValue() + b.doubleValue()));
        return a.doubleValue() + b.doubleValue();
    }
}
