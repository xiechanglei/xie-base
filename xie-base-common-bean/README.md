# xie-base-common-bean

`xie-base-common-bean` 模块是 `xie-base` 项目的子模块，主要用于提供一些通用的 `Bean` 对象。

引入依赖

```xml

<dependency>
    <groupId>io.github.xiechanglei</groupId>
    <artifactId>xie-base-common-bean</artifactId>
    <version>2.7.17.4</version>
</dependency>
```

### DataFit

数据填充类，用于生成一些动态的数据对象，通常用于controller中的动态返回对象，或者是测试数据的生成等等。

```java
// 生成一个动态的数据对象
 DataFit dataFit=DataFit.of("name","张三").fit("age",18);
// 支持转换url参数
         String urlParam=dataFit.toUrl();
```

### MessageResponse

消息响应类，用于controller中的返回对象.

通常情况下，我们为ajax返回的json对象，有以下常用字段

- **success** `boolean` 是否成功
- **message** `String` 提示信息
- **data** `Object` 数据对象
- **code** `Integer` 状态码,或者错误编码

```java
MessageResponse.success("data");
MessageResponse.fail("tip");
MessageResponse.fail("tip",10010);
```

### MessageException

消息级别的异常类

```java
throw new MessageException("tip");
throw new MessageException("tip",10010);
```

### ErrorDefinition
错误定义类，用于定义错误编码和错误信息，形成一个错误定义对象。

```java
public static final ErrorDefinition user_not_exitst =   ErrorDefinition.of(10010,"user_not_exitst");
```
