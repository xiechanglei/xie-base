# xie-base-common-digest DIGEST 模块

`xie-base-common-digest` 模块是 `xie-base` 项目的子模块，主要用于提供一些通用的加密和摘要算法。

引入依赖

```xml
<dependency>
    <groupId>io.github.xiechanglei</groupId>
    <artifactId>xie-base-common-base</artifactId>
    <version>2.7.17.4</version>
</dependency>
```

### ByteHelper, ByteArrayHelper

```java
// 16进制字符串转换为byte
byte b = ByteHelper.of("ff");
// byte转换为16进制字符串
String hex = ByteHelper.toHexString(b);
```

```java
// int数字转byte数组
byte[] arr = ByteArrayHelper.of(100,200,300,400);
byte[] arr = ByteArrayHelper.of(0x11,0x22,0x33,0x44);
// 16进制字符串转换为byte数组
byte[] arr = ByteArrayHelper.of("11223344");
byte[] arr = ByteArrayHelper.of("11 22 33 44");
// byte[] 转换为16进制字符串
String hex = ByteArrayHelper.toHexString(arr);
String hex = ByteArrayHelper.toHexString(arr,30);//从数组的第一个元素开始，转换30个
String hex = ByteArrayHelper.toHexString(arr,0,30); //从数组的第一个元素开始，转换30个
```

### DateHelper
日期工具类

Example1. 解析与格式化：
```java
Date d1 = DateHelper.parse("2012-12-12");
Date d2 = DateHelper.parse("2012-12-12 11:12:13");
String s1 = DateHelper.format(new Date());//2023-12-18 14:57:30

DateConverter converter = DateHelper.buildConverter("yyyy-MM-dd HH:mm:ss");
converter.parse("2012-12-12 11:12:13");
converter.format(new Date());
// 可以指定时区参数
DateConverter converter2 = DateHelper.buildConverter("yyyy-MM-dd HH:mm:ss",ZoneId.of("Asia/Shanghai"));
```

Example2. 时间加减:
```java
Date d1 = DateHelper.afterDays(3);//3天后
Date d2 = DateHelper.afterDays(new Date(),-3);//3天前
Date d3 = DateHelper.afterHours(24);//24小时后
// ...
```

Example3. 时间构建:
```java
DateHelper.getDateBuilder().set(Calendar.YEAR,2012).get();//把年份设置为2012
DateHelper.getDateBuilder().add(Calendar.YEAR,2).set(Calendar.MONTH,3).get();//两年后4月
DateHelper.getDateBuilder().startOfDay().get();//时间设置到当天的开始
DateHelper.getDateBuilder().endOfDay().get();//时间设置到当天的结束
DateHelper.getDateBuilder().startOfMonth().get();//时间设置到当月的开始
DateHelper.getDateBuilder().endOfMonth().get();//时间设置到当月的结束
// ...
```

Example4. 时间计算:
```java
DateHelper.countNatureDays(new Date(),new Date());//计算两个时间相差的自然天数,0
DateHelper.countNatureDays(new Date(),DateHelper.afterDays(3));//计算两个时间相差的自然天数,3
DateHelper.countNatureHours(new Date(),DateHelper.afterHours(3));//计算两个时间相差的自然小时数,3
//
DateHelper.getDatesBetween(new Date(), afterDays(100)).stream().map(DateHelper::format).forEach(System.out::println);//获取两个日期之间的所有天
```