package com.qzing.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Parallel-Streams 并行流
 * 流可以是顺序的，也可以是并行的。顺序流上的操作在单个线程上执行，而并行流上的操作在多个线程上并发执行。
 */
public class Test {
    public static void main(String[] args) {
        test2();
    }
    //Sequential Sort 顺序流排序
    public static void test1(){
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        // 纳秒
        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        // 纳秒转毫秒
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("顺序流排序耗时: %d ms", millis));
        //顺序流排序耗时: 700 ms
    }
    //Parallel Sort 并行流排序
    public static void test2(){
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        long t0 = System.nanoTime();
        long count = values.parallelStream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));
        //parallel sort took: 309 ms
    }


}
