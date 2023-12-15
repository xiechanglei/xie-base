# xie-base-common-json json 模块


`xie-base-common-json` 模块提供了一些 JSON 相关的工具类。
```xml
<dependency>
    <groupId>io.github.xiechanglei</groupId>
    <artifactId>xie-base-common-json</artifactId>
    <version>2.7.17.3</version>
</dependency>
```


### JsonHelper

`JsonHelper` 是一个工具类，提供了一些 JSON 相关的方法。

```java
// 将对象转换为 JSON 字符串
String json = JsonHelper.toJson(object);
// 将 JSON 字符串转换为对象
Object object = JsonHelper.fromJson(json, Object.class);
```

### 2.2.3. TextJsonContainer

`TextJsonContainer` 用于解析文本类型的json数据
```java
// 读取json数据
String json = "{name:\"zhangsan\"}";
// 创建 TextJsonContainer 对象
TextJsonContainer container = new TextJsonContainer(json);
// 获取 json 中的某个字段
String name = container.readJson("name",String.class);
```
