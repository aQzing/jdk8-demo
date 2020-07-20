package com.qzing.optionals;

import com.qzing.factionalinterface.Person;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 首先，Optional 它不是一个函数式接口，设计它的目的是为了防止空指针异常（NullPointerException）
 * 一个方法，这个方法返回的对象可能是空，也有可能非空的时候，你就可以考虑用 Optional 来包装它，这也是在 Java 8 被推荐使用的做法。
 */
public class Test {
    public static void main(String[] args) {
        Person p1 = null;
        Person p2 = new Person("小明",12);
        test2(p1);
        test2(p2);
    }

    public static void test1() {
        //of（）：为非null的值创建一个Optional
        Optional<String> optional = Optional.of("bam");
        // isPresent（）： 如果值存在返回true，否则返回false
        System.out.println(optional.isPresent());           // true
        //get()：如果Optional有值则将其返回，否则抛出NoSuchElementException
        optional.get();                 // "bam"
        //orElse（）：如果有值则将其返回，否则返回指定的其它值
        optional.orElse("fallback");    // "bam"
        //ifPresent（）：如果Optional实例有值则为其调用consumer，否则不做处理
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
    }
    public static void test2(Person p){
        //允许为空创建对象
        Optional<Person> p1 = Optional.ofNullable(p);
        //该对象不为空则消费他 ，为空则创建他
        if(p1.isPresent()){
            //存在 消费
            Consumer<Person>consumer = person -> {
                System.out.println("消费此人->"+person.getName());};
            consumer.accept(p1.get());
        }else{
            //不存在 生产
            Supplier<Person>supplier = Person::new;
            Person p2 = supplier.get();
            p2.setName("lihua");
            System.out.println("生产此人->"+p2.getName());
        }
    }

}
