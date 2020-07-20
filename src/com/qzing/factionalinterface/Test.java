package com.qzing.factionalinterface;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 函数式接口
 */
public class Test {
    public static void main(String[] args) {
        testA5();
    }
    public static void test1(){
        IConverter<String,Integer> ic = new IConverter<String,Integer>() {
            @Override
            public Integer convert(String numStr) {
                return Integer.valueOf(numStr);
            }
        };
        Integer convert =  ic.convert("13");
        System.out.println(convert instanceof Integer);
    }

    public static void test2(){
        IConverter<String,Integer> ic = (from) -> {
            return Integer.valueOf(from);
        };
        Integer convert =  ic.convert("13");
        System.out.println(convert instanceof Integer);
    }

    public static void test3(){
        IConverter<String,Integer> ic = from -> Integer.valueOf(from);
        Integer convert =  ic.convert("13");
        System.out.println(convert instanceof Integer);
    }

    public static void test4(){
        IConverter<String,Integer> ic =  Integer::valueOf;//这四个点::的关键字，不只是可以引用静态方法和构造函数，还可以引用对象方法。
        Integer convert =  ic.convert("13");
        System.out.println(convert instanceof Integer);
    }
    //=========================================================
    //Predicate 断言Predicate<test方法的参数类型>
    //Predicate 是一个可以指定入参类型，并返回 boolean 值的函数式接口。它内部提供了一些带有默认实现的方法，可以 被用来组合一个复杂的逻辑判断（and, or, negate）
    public static void testA1(){
//        Predicate<String> predicate = (s) -> s.length() > 0;
//
//        boolean foo0 = predicate.test("foo");           // true
//        boolean foo1 = predicate.negate().test("foo");  // negate否定相当于!true
//
//        Predicate<Boolean> nonNull = Objects::nonNull;
//        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        System.out.println(isEmpty.test(""));
        Predicate<String> isNotEmpty = isEmpty.negate();
    }
    //Functions
    //Function 函数式接口的作用是，我们可以为其提供一个原料，他给生产一个最终的产品。通过它提供的默认方法，组合,链行处理(compose, andThen)：
    public static void testA2(){
            Function<String, Integer> toInteger = Integer::valueOf;                                         //转Integer
            Function<String, Integer> add = toInteger.andThen(a->a+1);                    //+1

            Integer apply = toInteger.apply("123");// "123"
            Integer apply2 = add.apply("123");// "123"
            System.out.println(apply);
            System.out.println(apply2);
    }
    //Suppliers
    //Supplier 与 Function 不同，它不接受入参，直接为我们生产一个指定的结果，有点像生产者模式：
    public static void testA3(){
        Supplier<Person> personSupplier0 = Person::new;
        Person person = personSupplier0.get();// new Person
    }
    //Consumers
    public static void testA4(){
        // 参照物，方便知道下面的Lamdba表达式写法
        Consumer<Person> greeter01 = new Consumer<Person>() {
            @Override
            public void accept(Person p) {
                System.out.println("Hello, " + p.getName());
            }
        };
        Consumer<Person> greeter02 = (p) -> System.out.println("Hello, " + p.getName());
        greeter02.accept(new Person("Luke", 33));  //Hello, Luke
        Consumer<Person> greeter03 = new MyConsumer<Person>()::accept;    // 也可以通过定义类和方法的方式去调用，这样才是实际开发的姿势
        greeter03.accept(new Person("Luke", 77));  //Hello, Luke
    }
    // Comparators
    public static void testA5(){
        Comparator<Person> comparator01 = (p1, p2) -> p1.getAge().compareTo(p2.getAge());
        Comparator<Person> comparator02 = Comparator.comparing(p -> p.getAge());           //等同于上面的方式
        Person p1 = new Person("John", 12);
        Person p2 = new Person("Alice", 32);
        int compare = comparator01.compare(p1, p2);
        int compare2  = comparator02.reversed().compare(p1, p2);
        System.out.println(compare);
        System.out.println(compare2);
    }
}
