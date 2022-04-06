package com.hqm.rabbit.utils.test.基础;

public class 语法基础 {

    public static void main(String[] args) {
        a();
        b();
        f();
    }


    //a = a + b 与 a += b 的区别 += 隐式的将加操作的结果类型强制转换为持有结果的类型。
    //如果两这个整型相加，如 byte、short 或者 int，首先会将它们提升到 int 类型，然后在执行加法操作。
    //a = a + b 与 a += b 的区别
    public static void  a() {
        byte a = 127;
        byte b = 127;
        int i = a + b;
        //b = a + b; // error : cannot convert from int to byte
        b += a; // ok
        System.out.println(i);
        //因为 a+b 操作会将 a、b 提升为 int 类型，所以将 int 类型赋值给 byte 就会编译出错
    }
    //3*0.1 == 0.3 将会返回什么? true 还是 false?
    public static void  b() {
        if(3*0.1==0.3){
            System.out.println(true);
        }else{
            System.out.println(false);
        }
        //false，因为有些浮点数不能完全精确的表示出来。
    }
    public static void  c() {
        // 能在 Switch 中使用 String 吗?
        // 从 Java 7 开始，我们可以在 switch case 中使用字符串，但这仅仅是一个语法糖。内部实现在 switch 中使用字符串的 hash code。
    }
    public static void d(){
        //对equal()和hashCode()的理解? 为什么在重写 equals 方法的时候需要重写 hashCode 方法?
        // 因为有强制的规范指定需要同时重写 hashcode 与 equal 是方法，许多容器类，如 HashMap、HashSet 都依赖于 hashcode 与 equals 的规定。
        // 有没有可能两个不相等的对象有有相同的 hashcode? 有可能，两个不相等的对象可能会有相同的 hashcode 值，这就是为什么在 hashmap 中会有冲突
        // 。相等 hashcode 值的规定只是说如果两个对象相等，必须有相同的hashcode 值，但是没有关于不相等对象的任何规定。
        // 两个相同的对象会有不同的的 hash code 吗? 不能，根据 hash code 的规定，这是不可能的。
    }
    //String、StringBuffer与StringBuilder的区别？
    public static void f(){
        /*第一点: 可变和适用范围。String对象是不可变的，而StringBuffer和StringBuilder是可变字符序列。每次对String的操作相当于生成一个新的String对象，
        而对StringBuffer和StringBuilder的操作是对对象本身的操作，而不会生成新的对象，所以对于频繁改变内容的字符串避免使用String，因为频繁的生成对象将会对系统性能产生影响。
        第二点: 线程安全。String由于有final修饰，是immutable的，安全性是简单而纯粹的。StringBuilder和StringBuffer的区别在于StringBuilder不保证同步，
        也就是说如果需要线程安全需要使用StringBuffer，不需要同步的StringBuilder效率更高。*/
        long startTime = System.currentTimeMillis();    //获取开始时间
        StringBuffer str=new StringBuffer();
        for(int i=0;i<9999;i++){
            str.append(i);
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("StringBuffer程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间


        long startTimea = System.currentTimeMillis();    //获取开始时间
        StringBuilder stra=new StringBuilder();
        for(int i=0;i<9999;i++){
            stra.append(i);
        }
        long endTimea = System.currentTimeMillis();    //获取结束时间
        System.out.println("StringBuilder程序运行时间：" + (endTimea - startTimea) + "ms");    //输出程序运行时间



        long startTimeb = System.currentTimeMillis();    //获取开始时间
        String strb="";
        for(int i=0;i<9999;i++){
            strb+=i;
        }
        long endTimeb = System.currentTimeMillis();    //获取结束时间
        System.out.println("String程序运行时间：" + (endTimeb - startTimeb) + "ms");    //输出程序运行时间

    }



}
