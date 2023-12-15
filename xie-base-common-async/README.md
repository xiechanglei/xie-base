# xie-base-common-async
异步工具类，提供了异步返回值的封装，以及异步执行的工具类。

引入依赖
```xml
<dependency>
    <groupId>io.github.xiechanglei</groupId>
    <artifactId>xie-base-common-async</artifactId>
    <version>2.7.17.2</version>
</dependency>
```

### AsyncLock 异步锁
提供了一个异步锁，可以在异步请求中，等待锁的释放。
Examples:
```java
public class Test {
    public static void main(String[] args) {
        AsyncLock asyncLock = new AsyncLock<>();
        new Thread(() -> {
            Thread.sleep(3000);
            asyncLock.unlock();
        }).start();
        asyncLock.lock(1000 * 10);
        System.out.println("end");
    }
}
```

### LockAsyncMessageProducer 异步请求返回封装 
Examples:
```java
public class Test {
    public static void main(String args[]) {
        LockAsyncMessageProducer<String, String> lockAsyncMessageProducer = new LockAsyncMessageProducer<>();
        String requestId = "testkey"; // 这里是唯一的requestId
        //模拟异步请求
        new Thread(() -> lockAsyncMessageProducer.put(requestId, "hello world")).start();
        String result = lockAsyncMessageProducer.wait(requestId, 30 * 1000);
        System.out.println(result);
    }
}
```
当你可以做到requestId全局唯一的时候，可以使用默认提供的`GlobalLockAsyncMessage`,

Examples:
```java
ublic class Test {
    public static void main(String args[]) {
        String requestId = "testkey"; // 这里是唯一的requestId
        //模拟异步请求
        new Thread(() -> GlobalLockAsyncMessage.put(requestId, "hello world")).start();
        String result = GlobalLockAsyncMessage.wait(requestId, 30 * 1000);
        System.out.println(result);
    }
}
```