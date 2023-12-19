# xie-base-common-http

`xie-base-common-http` 模块是 `xie-base` 项目的子模块，主要用于提供一些通用的HTTP请求工具类。

引入依赖

```xml
<dependency>
    <groupId>io.github.xiechanglei</groupId>
    <artifactId>xie-base-common-http</artifactId>
    <version>2.7.17.4</version>
</dependency>
```

### HttpHelper

HTTP请求工具类，用于发送HTTP请求。

```java
// 构建请求终端
HttpRequest request = HttpHelper.build()
// 模拟浏览器请求终端
HttpRequest request = HttpHelper.buildBrowserClient();
// 添加请求参数
request.transfer(t -> t.param("name", "张三").header("age", 18).cookie("c1",""));
// 发送请求
HttpResponse response = request.get("http://www.baidu.com");
// 获取响应内容
String body = response.body();
// 解析响应json
String name = response.readJson("$.name");
Animal animal = response.readJson(Animal.class);
Animal animal = response.readJson("$.animal", Animal.class);
// 解析html
Document document = response.parse();
String logo = response.select("img#logo").attr("src");
//保存文件
response.save("/tmp/baidu.html");
```
