# xie-base-common-digest DIGEST 模块

## 1. 介绍

`xie-base-common-digest` 模块是 `xie-base` 项目的子模块，主要用于提供一些通用的加密和摘要算法。

## 2. 使用

### 2.1. 引入依赖

```xml
<dependency>
    <groupId>com.xiechanglei.code</groupId>
    <artifactId>xie-base-common-digest</artifactId>
    <version>${xie-base.version}</version>
</dependency>
```

### 2.2. 使用示例

#### 2.2.1. `com.xiechanglei.code.base.common.digest.md5.MD5Helper`

MD5加密工具类，用于生成MD5加密的字符串。

```
// 生成一个MD5加密的字符串
String md5 = MD5Util.md5("123456");
```

#### 2.2.2. `com.xiechanglei.code.base.common.digest.aes.AESHelper`

AES加密工具类，用于生成AES加密的字符串。

```
// 生成一个AES加密的字符串
String secret = AESHelper.encode("123456","password","iv-content");
// 解密
String content = AESHelper.decode(secret,"password","iv-content");
```