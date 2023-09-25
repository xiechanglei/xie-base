# xie-base-web web 模块

## 1. 介绍

`xie-base-reflect` 模块提供了一些 spring boot web 的基础配置

## 2. 使用

### 2.1. 引入依赖

```xml
<dependency>
    <groupId>com.xiechanglei.code</groupId>
    <artifactId>xie-base-web</artifactId>
    <version>${xie-base.version}</version>
</dependency>
```

### 2.2. 特性

#### 2.2.1. 默认日期类型输出配置

默认配置spring boot `spring.jackson.serialization.write-dates-as-timestamps=true` ， 将时间类型的字段返回程日期类型

#### 2.2.2. 默认日期类型参数配置

默认满足日期类型的参数解析,如下：

```java
@RequestMapping("/test")
public void test(Date date){
    System.out.println(date);
}
```

请求参数为：`/test?date=2020-01-01`，或者`/test?date=2020-01-01 12:12:12`

也可以是timestamp类型的参数，如：`/test?date=1577836800000`
但是需要修改spring boot的配置，如下：

```properties
# 默认日期类型参数解析模式 pattern | timestamp,默认为pattern
com.xiechanglei.code.base.web.read-date-type=timestamp
```

#### 2.2.3. 分页请求参数默认配置

默认满足分页请求参数解析，如下：

```java
@RequestMapping("/test")
public void test(Integer page, Integer size){
    System.out.println(pageable);
}
```

通常在项目中，page，size都会做一些固定的默认处理，如page，size没有传递参数值的时候，默认为1,20

可以使用spring boot的@RequestParam 进行注解配置，这里增加了一种全局的参数化配置方案(spring boot)：
```properties
# 分页page参数，默认1
com.xiechanglei.code.base.web.page=1
# 分页size参数，默认20
com.xiechanglei.code.base.web.size=20
```