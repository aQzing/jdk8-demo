package com.qzing.annotations;

/**
 * Java8中的注释是可重复的。让我们直接深入到一个例子中来解决这个问题。{在SpringBoot的启动类中就可以看到这中类型的注解}
 */
public class Test {
    public static void main(String[] args) {
        test();
    }
    //第一种形态：使用注解容器（老方法）
    public static void test1(){
        @Hints({@Hint("hint1"), @Hint("hint2")})
        class Person {
        }
    }
    //第二种形态：使用可重复注解（新方法）
    public static void test2() {
        @Hint("hint1")
        @Hint("hint2")
        class Person {
        }
    }
    public static void test(){
        @Hint("hint1")
        @Hint("hint2")
        class Person {
        }
        Hint hint = Person.class.getAnnotation(Hint.class);
        System.out.println(hint);                   // null
        Hints hints1 = Person.class.getAnnotation(Hints.class);
        System.out.println(hints1.value().length);  // 2
        Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
                System.out.println(hints2.length);          // 2
    }
}
