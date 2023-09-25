# xie-base-common-bean BEAN 模块

## 1. 介绍

`xie-base-common-bean` 模块是 `xie-base` 项目的子模块，主要用于提供一些通用的 `Bean` 对象。

## 2. 使用

### 2.1. 引入依赖

```xml
<dependency>
    <groupId>com.xiechanglei.code</groupId>
    <artifactId>xie-base-common-bean</artifactId>
    <version>${xie-base.version}</version>
</dependency>
```

### 2.2. 使用示例

#### 2.2.1. `com.xiechanglei.code.base.common.bean.messag.DataFit`

数据填充类，用于生成一些动态的数据对象，通常用于controller中的动态返回对象，或者是测试数据的生成等等。

```java
// 生成一个动态的数据对象
 DataFit dataFit = new DataFit().of("name", "张三").fit("age", 18);
// 支持转换url参数
 String urlParam = dataFit.toUrl();
```

#### 2.2.2. `com.xiechanglei.code.base.common.bean.messag.MessageResponse`

消息响应类，用于controller中的返回对象.

通常情况下，我们为ajax返回的json对象，指定了三个字段，success，message，data，分别表示是否成功，消息，数据。

```java
// 生成一个消息响应对象,覆盖绝大部分场景
 MessageResponse.success("data");
// 支持增加提示信息，极少情况下使用
 MessageResponse.success("data","tip");
// 失败信息，覆盖绝大部分场景
 MessageResponse.fail("tip");
// 失败信息，添加数据，极少情况下使用
 MessageResponse.fail("tip","data");
```
