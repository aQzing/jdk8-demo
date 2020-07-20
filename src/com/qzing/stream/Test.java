package com.qzing.stream;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 我们可以使用 java.util.Stream 对一个包含一个或多个元素的集合做各种操作。这些操作可能是 中间操作 亦或是 终端操作。 终端操作会返回一个结果，而中间操作会返回一个 Stream 流。
 *
 * 需要注意的是，你只能对实现了 java.util.Collection 接口的类做流的操作。
 *
 * Stream 流支持同步执行，也支持并发执行。
 *
 * 注意：Map不支持Stream流，但是他的key和value是支持的！
 */
public class Test {
    public static void main(String[] args) {
        test5();
    }
    //Filter 过滤
    /**
     * Filter 的入参是一个 Predicate, 上面已经说到，Predicate 是一个断言的中间操作，它能够帮我们筛选出我们需要的集合元素。它的返参同样 是一个 Stream 流，我们可以通过 foreach 终端操作，来打印被筛选的元素：
     */
    public static void test1(){
    //筛选出集合中带有a的数据
        List <String>list = Arrays.asList("df","ad","sf","lambda");
        List<String> a = list.stream().filter(s -> s.contains("a")).collect(Collectors.toList());
        //Consumer<String> println = System.out::println;
        //a.forEach(println);
        a.forEach(System.out::println);
    }
    //Sorted 排序
    /**
     * Sorted 同样是一个中间操作，它的返参是一个 Stream 流。另外，我们可以传入一个 Comparator 用来自定义排序，如果不传，则使用默认的排序规则
     */
    public static void test2(){
        List <String>list = Arrays.asList("df","ad","sf","lambda");
        list.stream().sorted().forEach(System.out::println);
    }
    //Map 转换
    /**
     * 顾名思义，match 用来做匹配操作，它的返回值是一个 boolean 类型。通过 match, 我们可以方便的验证一个 list 中是否存在某个类型的元素。
     */
    public static void test3(){
    //Match 匹配
        //检测集合中是否有a开头的数据
        List <String>list = Arrays.asList("df","ad","sf","lambda");
        boolean a = list.stream().anyMatch(ss -> ss.startsWith("a"));
        System.out.println(a);
    }
    //Count 计数
    public static void test4(){
        //统计集合中包含a的元素个数
        long count = Arrays.asList("df", "ad", "asf", "lambda").stream().filter(ss -> ss.startsWith("a")).count();
        System.out.println(count);

    }
    //Reduce
    /**
     * Reduce 中文翻译为：减少、缩小。通过入参的 Function，我们能够将 list 归约成一个值。它的返回类型是 Optional 类型
     */

    public static void test5(){
        //计算1-100结果
        int max = 100;
        ArrayList <Integer>arrayList = new  <Integer>ArrayList(max);
        for (int i = 1; i <=max ; i++) {
            arrayList.add(i);
        }
        Optional<Integer> reduce = arrayList.stream().reduce((s1, s2) -> s1 + s2);
        System.out.println(reduce.get());
        reduce.ifPresent(System.out::println);
    }


}
