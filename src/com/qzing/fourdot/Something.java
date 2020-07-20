package com.qzing.fourdot;

public class Something{

     Something(String something) {

        System.out.println("进入构造方法1->"+something);
    }

     Something(Integer something) {
        System.out.println("进入构造方法2->"+something);
    }

    Something( ) {
        System.out.println("进入构造方法3->");
    }

    public static String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
    public  String startsWith2(String s) {
        return String.valueOf(s.charAt(0));
    }

}