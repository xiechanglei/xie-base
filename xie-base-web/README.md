# xie-base-web web 模块

## 1. 介绍

`xie-base-reflect` 模块提供了一些 spring boot web 的基础配置,是基于spring-boot-starter 的一个脚手架

## 2. 使用

### 2.1. 引入依赖

```xml
<dependency>
    <groupId>io.github.xiechanglei</groupId>
    <artifactId>xie-base-web</artifactId>
    <version>2.7.17.4</version>
</dependency>
```

### 2.2. 特性

#### 2.2.1. 日期类型输出配置

默认配置spring boot：
```properties
spring.jackson.serialization.write-dates-as-timestamps=true
```
将时间类型的字段输出为时间戳。

如果你的项目需要使用其他的类型的值，请覆盖此配置的值即可。

#### 2.2.2. 接收日期类型参数配置

默认满足日期类型的参数解析,如下：

```java
@RequestMapping("/test")
public void test(Date date){
    System.out.println(date);
}
```
请求参数可以是以下类型的值:
- `/test?date=2020-01-01` 年月日类型
- `/test?date=2020-01-01 12:12:12` 年月日时分秒类型
- `/test?date=1577836800000`  时间戳类型

使用以下配置关闭此功能:
```properties
io.github.xiechanglei.base.web.use-date-resolver=false
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
io.github.xiechanglei.base.web.page-default-page=1
# 分页size参数，默认20
io.github.xiechanglei.base.web.page-default-size=20
```
使用以下配置关闭此功能:
```properties
io.github.xiechanglei.base.web.use-page-resolver=false
```

#### 2.2.4. 接口返回类型封装
通常的项目开发中，接口的返回值一般是固定的格式，如：

```json
{
  "code": 0,
  "success": true,
  "message": null,
  "data": "hello world"
}
```
一般情况下，很多项目是以下方式处理的：

```java
@RequestMapping("test")
public MessageResponse test(){
    return new MessageResponse(true,"hello world");    
}
```
这里，我们将此过程做了自动化封装的处理,修改上述代码：
```java
@RequestMapping("test")
public String test(){
    return "hello world";    
}
```
程序依然会返回约定的json结构。

这里有几个默认的约定，
- 当code为0的时候，表示程序按照预期返回，并且success的值为true，message为null
- 当方法或者类上增加了 `@NoResponseAdvice`注解的时候，程序不再接管此方法或者此类下所有的方法的返回封装
- 当方法的返回值类型是`MessageResponse`的时候，程序不处理封装过程
- 当接口需要返回失败信息的时候，可以通过抛出`MessageException`来解决：
```java
throw new MessageException("系统未知错误");//{"code":-1,"message":"系统未知错误","success":false}
throw new MessageException("用户不存在",1000);//{"code":1000,"message":"系统未知错误","success":false}
// kill new
throw MessageException.of("系统未知错误");//{"code":-1,"message":"系统未知错误","success":false}
throw MessageException.of("用户不存在",1000);//{"code":1000,"message":"用户不存在","success":false}
//use constans
public static USER_NOT_EXISTS = ErrorDefinition.of(1000,"用户不存在");
throw MessaheException.of(USER_NOT_EXISTS);//{"code":100,"message":"用户不存在","success":false}
```

如果需要关闭此功能，可以使用以下配置:
```properties
io.github.xiechanglei.base.web.response-advice=false
```

#### 2.2.5 全局异常处理
如果需要关闭此功能，可以使用以下配置:
```properties
io.github.xiechanglei.base.web.exception-advice=false
```

#### 2.2.6 全局跨域配置
默认已经开启了跨域配置，如果需要关闭此功能，可以使用以下配置:
```properties
io.github.xiechanglei.base.web.cors=false
```
跨域配置的默认值如下,可以自行修改：
```properties
io.github.xiechanglei.base.web.cross-headers=*
io.github.xiechanglei.base.web.cross-origins=*
io.github.xiechanglei.base.web.cross-headers=your-header
```