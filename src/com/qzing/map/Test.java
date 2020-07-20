package com.qzing.map;

import com.qzing.factionalinterface.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Map是不支持 Stream 流的，因为 Map 接口并没有像 Collection 接口那样，定义了 stream() 方法。但是，我们可以对其 key, values, entry 使用 流操作，如 map.keySet().stream(), map.values().stream() 和 map.entrySet().stream().
 */
public class Test {
    public static void main(String[] args) {
        test6();
    }

    //JDK 8 中对 map 提供了一些其他新特性:
    public static void test1() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            // 与老版不同的是，putIfAbent() 方法在 put 之前，  不用在写if null continue了
            // 会判断 key 是否已经存在，存在则直接返回 value, 否则 put, 再返回 value
            map.putIfAbsent(i, "val" + i);
        }
        // forEach 可以很方便地对 map 进行遍历操作
        map.forEach((key, value) -> System.out.println(value));
    }

    public static void test2() {
        Map<Integer, Person> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            // 与老版不同的是，putIfAbent() 方法在 put 之前，  不用在写if null continue了
            // 会判断 key 是否已经存在，存在则直接返回 value, 否则 put, 再返回 value
            map.putIfAbsent(i, new Person("aaa" + i, i));
        }
        Stream<Person> beanBStream00 = map.values().stream().map(new Function<Person, Person>() {
            @Override
            public Person apply(Person beanA) {
                return new Person(beanA.getName(), beanA.getAge());
            }
        });
        Stream<Person> beanBStream01 = map.values().stream().map(beanA -> new Person(beanA.getName(), beanA.getAge()));
        beanBStream01.forEach(System.out::println);
    }

    //除了上面的 putIfAbsent() 和 forEach() 外，我们还可以很方便地对某个 key 的值做相关操作：
    public static void test3() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            // 与老版不同的是，putIfAbent() 方法在 put 之前，  不用在写if null continue了
            // 会判断 key 是否已经存在，存在则直接返回 value, 否则 put, 再返回 value
            map.putIfAbsent(i, i+2);
        }
        // 如下：对 key 为 3 的值，内部会先判断值是否存在，存在，则做 value + key 的拼接操作
        map.computeIfPresent(3, (num, val) -> val + num);
        map.get(3);

        // 先判断 key 为 9 的元素是否存在，存在，则做删除操作
        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9);     // false

        // computeIfAbsent(), 当 key 不存在时，才会做相关处理
        // 如下：先判断 key 为 23 的元素是否存在，不存在，则添加
        map.computeIfAbsent(23, num ->  999);
        System.out.println(map.get(23));    //

        // 先判断 key 为 3 的元素是否存在，存在，则不做任何处理
        map.computeIfAbsent(23, num -> 888);
        System.out.println(map.get(23));

    }
    //关于删除操作，JDK 8 中提供了能够新的 remove() API:
    public static void test4(){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            // 与老版不同的是，putIfAbent() 方法在 put 之前，  不用在写if null continue了
            // 会判断 key 是否已经存在，存在则直接返回 value, 否则 put, 再返回 value
            map.putIfAbsent(i, i+2);
        }

        map.remove(3, "val3");
        map.get(3);             // val33

        map.remove(3, "val33");
        map.get(3);             // null
    }
    //关于添加方法，JDK 8 中提供了带有默认值的 getOrDefault() 方法：
    public static void test5(){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            // 与老版不同的是，putIfAbent() 方法在 put 之前，  不用在写if null continue了
            // 会判断 key 是否已经存在，存在则直接返回 value, 否则 put, 再返回 value
            map.putIfAbsent(i, i+2);
        }
        // 若 key 42 不存在，则返回 -1
        System.out.println(map.getOrDefault(42, -1));  // -1
    }
    public static void test6(){
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            // 与老版不同的是，putIfAbent() 方法在 put 之前，  不用在写if null continue了
            // 会判断 key 是否已经存在，存在则直接返回 value, 否则 put, 再返回 value
            map.putIfAbsent(i, i+"wq");
        }
        // merge 方法，会先判断进行合并的 key 是否存在，不存在，则会添加元素
        map.merge(111, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(111));             // val9
        // 若 key 的元素存在，则对 value 执行拼接操作
        map.merge(111, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(111));            // val9concat
    }
}
