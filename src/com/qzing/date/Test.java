package com.qzing.date;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.text.AttributeSet;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * Java 8 中在包 java.time 下添加了新的日期 API. 它和 Joda-Time 库相似，但又不完全相同
 */
public class Test {
    public static void main(String[] args) {
        test5C();;

    }

    //Clock
    /**
     * Clock 提供对当前日期和时间的访问。我们可以利用它来替代 System.currentTimeMillis() 方法。另外，通过 clock.instant() 能够获取一个 instant 实例， 此实例能够方便地转换成老版本中的 java.util.Date 对象。
     */
    public static void test1(){
        Clock clock = Clock.systemDefaultZone();
        Instant instant = clock.instant();
        Date date = Date.from(instant);

    }
    //Timezones 时区
    /**
     * ZoneId 代表时区类。通过静态工厂方法方便地获取它，入参我们可以传入某个时区编码。另外，时区类还定义了一个偏移量，用来在当前时刻或某时间 与目标时区时间之间进行转换
     */
    public static void test2(){
        System.out.println(ZoneId.getAvailableZoneIds());
        // prints all available timezone ids
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
    }
    //LocalTime

    /**
     * LocalTime 表示一个没有指定时区的时间类，例如，10 p.m.或者 17：30:15，下面示例代码中，将会使用上面创建的 时区对象创建两个 LocalTime。然后我们会比较两个时间，并计算它们之间的小时和分钟的不同
     */
    public static void test3A(){
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2));  // false
        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        System.out.println(hoursBetween);       // -3
        System.out.println(minutesBetween);     // -239
    }
    public static void test3B(){
        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);       // 23:59:59
        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedTime(FormatStyle.SHORT)
                        .withLocale(Locale.GERMAN);
        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime);   // 13:37

    }
    //LocalDateTime
    /**
     * LocalDate 是一个日期对象，例如：2014-03-11。它和 LocalTime 一样是个 final 类型对象。下面的例子演示了如何通过加减日，月，年等来计算一个新的日期
     */
    public static void test4A(){
        LocalDate today = LocalDate.now();
        // 今天加一天
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        // 明天减两天
        LocalDate yesterday = tomorrow.minusDays(2);
        // 2014 年七月的第四天
        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek);    // 星期五
    }
    //也可以直接解析日期字符串，生成 LocalDate 实例。（和 LocalTime 操作一样简单）
    public static void test4B(){
        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.MEDIUM)
                        .withLocale(Locale.GERMAN);
        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
        System.out.println(xmas);   // 2014-12-24
    }
    //LocalDateTime
    /**
     * LocalDateTime 是一个日期-时间对象。你也可以将其看成是 LocalDate 和 LocalTime 的结合体。操作上，也大致相同。
     */
    public static void test5A(){
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);      // 星期三
        Month month = sylvester.getMonth();
        System.out.println(month);          // 十二月
        // 获取改时间是该天中的第几分钟
        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);    // 1439
    }

    /**
     * 如果再加上的时区信息，LocalDateTime 还能够被转换成 Instance 实例。Instance 能够被转换成老版本中 java.util.Date 对象。
     */
    public static void test5B(){
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
        Instant instant = sylvester
                .atZone(ZoneId.systemDefault())
                .toInstant();
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014
    }

    /**
     * 格式化 LocalDateTime 对象就和格式化 LocalDate 或者 LocalTime 一样。除了使用预定义的格式以外，也可以自定义格式化输出。
     */
    public static void test5C(){
        DateTimeFormatter formatter =
                DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss");
        //LocalDateTime parsed = LocalDateTime.now();
        LocalDateTime parsed = LocalDateTime.parse("2020-07-17 14:17:35",formatter);
        System.out.println(parsed);
        String string = formatter.format(parsed.minusDays(2));
        System.out.println(string);
    }
}

