# xie-base-common-function

函数式编程相关的工具类

引入依赖

```xml
<dependency>
    <groupId>io.github.xiechanglei</groupId>
    <artifactId>xie-base-common-function</artifactId>
    <version>2.7.17.4</version>
</dependency>
```

### SimpleFunction
Example:
```java
public static void main(String[]args){
    SimpleFunction<Integer, String> doSomeThing = data -> "zhangsan";
    String res = doSomeThing(123);
}
```

### SimpleVoidFunction
Example:
```java
public static void main(String[]args){
    SimpleVoidFunction<String> log = System.out::println;
    log("zhangsan");
}
```

### ComposeFunction
Example:
```java

public static void main(String[]args){
    ComposeFunction<Integer, Integer> max = (x,y) -> x > y ? x:y;
    max(1,2);
}
```
