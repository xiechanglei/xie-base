# xie-base-common-reflect reflect 模块

`xie-base-common-reflect` 模块提供了一些与反射相关的工具类。

引入依赖

```xml
<dependency>
    <groupId>io.github.xiechanglei</groupId>
    <artifactId>xie-base-common-reflect</artifactId>
    <version>2.7.17.3</version>
</dependency>
```

### FieldHelper

`FieldHelper` 是一个工具类，提供了一些与字段相关的方法。

```java
// 判断某个字段是否包含了指定的任何一个注解
boolean hasAnnotation = FieldHelper.hasAnnotation(field, Annotation1.class, Annotation2.class);
```