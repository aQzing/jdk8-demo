package com.qzing.fourdot;

/**
 * 四饼符
 */
public class Test {
    public static void main(String[] args) {
        test3();
    }
    //::访问静态方法
    public static void test1(){
        Utils<String, String>u = Something::startsWith;
        String s = u.run("你asdfadsdfs");
        System.out.println(s);
    }
    //::访问对象方法
    public static void test2(){
        Something something = new Something("构造值");
        Utils<String, String>u = something::startsWith2;
        String s = u.run("你的粉丝");
        System.out.println(s);
    }
    //::访问构造方法
    public static void test3(){
        ////Utils<构造函数参数类型，创建实例类型>
        Utils<String, Something>u = Something::new;
        Utils<Integer, Something>u2 = Something::new;
        //调用时候才会实例化
        Something s = u.run("hello");
        Something s2 = u2.run(888);
    }

}
