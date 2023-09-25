# xie-base-common-reflect reflect 模块

## 1. 介绍

`xie-base-common-reflect` 模块提供了一些与反射相关的工具类。

## 2. 使用

### 2.1. 引入依赖

```xml
<dependency>
    <groupId>com.xiechanglei.code</groupId>
    <artifactId>xie-base-common-reflect</artifactId>
    <version>${xie-base.version}</version>
</dependency>
```

### 2.2. 使用示例

#### 2.2.1. `com.xiechanglei.code.base.common.reflect.FieldHelper`

`FieldHelper` 是一个工具类，提供了一些与字段相关的方法。

```java
// 判断某个字段是否包含了指定的任何一个注解
boolean hasAnnotation = FieldHelper.hasAnnotation(field, Annotation1.class, Annotation2.class);
// 获取第一个拥有指定注解的字段的值
String name = FieldHelper.getFiledValueByAnnotation(object, Annotation1.class);