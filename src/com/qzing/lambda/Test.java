package com.qzing.lambda;

import com.qzing.factionalinterface.IConverter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lambda表达式
 */
public class Test {
    private int outerNum = 2;
    private static int outerStaticNum = 2;
    public static void main(String[] args) {
        //List<String> list = Arrays.asList("AA","SFS","ABC","WER");
        //testSuper(list);
        testC();
    }
    //原始方法给list集合逆序排序
    public static void test(List list){
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        list.forEach(System.out::println);
    }
    public static void testB(List list){
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        list.forEach(System.out::println);
    }
    //用lambda改造
    public static void test1(List list){
        Collections.sort(list,((String o1, String o2) -> o2.compareTo(o1)));
        list.forEach(System.out::println);
    }
    //用lambda改造
    public static void testB2(List<String> list){
        list.sort((o1, o2) -> o2.compareTo(o1));
        list.forEach(System.out::println);
    }
    //使用默认的方法
    public static void testSuper(List<String> list){
        list.sort(Comparator.reverseOrder());
        list.forEach(System.out::println);
    }
    //lambda访问局部变量 不可修改
    public static void testC(){
        int a = 2;
        IConverter<Integer,String>ic = b->String.valueOf(b+a);
        String result = ic.convert(2);
        System.out.println(result);
        //不能修改局部变量了，当然在lambda表达式内部也不允许修改
        //a = 5;
    }
    //访问静态变量和成员变量  这里lambda对其有修改的权限
    public  void testC1() {

        IConverter<Integer, String> stringConverter1 = (from) -> {
            // 对成员变量赋值
            outerNum = 23;
            return String.valueOf(from);
        };

    }
    public static void testC2() {
        IConverter<Integer, String> stringConverter2 = (from) -> {
            // 对静态变量赋值
            outerStaticNum = 72;
            return String.valueOf(from);
        };

    }


}
