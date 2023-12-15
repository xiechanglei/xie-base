# xie-base-common-bean BEAN 模块

函数式编程相关的工具类

引入依赖

```xml
<dependency>
    <groupId>io.github.xiechanglei</groupId>
    <artifactId>xie-base-common-function</artifactId>
    <version>2.7.17.2</version>
</dependency>
```

### SimpleFunction
Example:
```java
public static void doSomeThing(Integer data, SimpleFunction<Integer, String> function) {
    String result = function.process(data);
}

public static void main(String[]args){
    doSomeThing(123, data -> "zhangsan");
}
```

### ComposeFunction
Example:
```java
public static void doSomeThing(Integer data1,Integer data2, ComposeFunction<Integer, String> function) {
    String result = function.process(data1,data2);
}
public static void main(String[]args){
    doSomeThing(123,456, (data1,data2) -> "zhangsan");
}
```
