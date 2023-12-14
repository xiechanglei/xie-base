# xie-base-common-json json 模块

## 1. 介绍

`xie-base-common-json` 模块提供了一些 JSON 相关的工具类。

## 2. 使用

### 2.1. 引入依赖

```xml
<dependency>
    <groupId>com.xiechanglei.code</groupId>
    <artifactId>xie-base-common-json</artifactId>
    <version>${xie-base.version}</version>
</dependency>
```

### 2.2. 使用示例

#### 2.2.1. `io.github.xiechanglei.base.common.json.JsonHelper`

`JsonHelper` 是一个工具类，提供了一些 JSON 相关的方法。

```java
// 将对象转换为 JSON 字符串
String json = JsonHelper.toJson(object);
// 将 JSON 字符串转换为对象
Object object = JsonHelper.fromJson(json, Object.class);
```

#### 2.2.2. `io.github.xiechanglei.base.common.json.JsonContainerAdapter`

`JsonContainerAdapter` 是一个抽象类，抽象了一个json容器，提供了一些方法，用于方便的读取json中的数据

#### 2.2.3. `io.github.xiechanglei.base.common.json.TextJsonContainer`

`TextJsonContainer` 是 `JsonContainerAdapter` 的一个实现类，用于读取文本类型的json数据

```java
// 读取json数据
String json = ...;
// 创建 TextJsonContainer 对象
TextJsonContainer container = new TextJsonContainer(json);
// 获取 json 中的某个字段
String name = container.readJson("name",String.class);
```
