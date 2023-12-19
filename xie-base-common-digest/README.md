# xie-base-common-digest

`xie-base-common-digest` 模块是 `xie-base` 项目的子模块，主要用于提供一些通用的加密和摘要算法。

引入依赖

```xml
<dependency>
    <groupId>io.github.xiechanglei</groupId>
    <artifactId>xie-base-common-digest</artifactId>
    <version>2.7.17.4</version>
</dependency>
```

### MD5Helper

MD5加密工具类，用于生成MD5加密的字符串。

```java
// 生成一个MD5加密的字符串
String md5 = MD5Util.md5("123456");
```

### AESHelper

AES加密工具类，用于生成AES加密的字符串。

```java
// 生成一个AES加密的字符串
String secret = AESHelper.encode("123456","password","iv-content");
// 解密
String content = AESHelper.decode(secret,"password","iv-content");
```

### CRC16 , CRC32
用于CRC16和CRC32的校验码生成。

```java
// 直接根据byte生成校验码
int crc16 = CRC16.calculate("helloworld".getBytes());
// 直接根据byte生成校验二进制数组
byte[] crc16Bytes = CRC16.calculateToByteArray("helloworld".getBytes());
// 装填模式
CRC16 crc16 = new CRC16();
crc16.update("helloworld1".getBytes());
crc16.update("helloworld2".getBytes());
crc16.getValue();
crc16.getValueToByteArray();

// CRC32，默认多项式为0xEDB88320L，也可以自己传入多项式
// 直接根据byte生成校验码
int crc32 = CRC32.calculate("helloworld".getBytes());//calculate("helloworld".getBytes(),0x04C11DB7L)
// 直接根据byte生成校验二进制数组
byte[] crc32Bytes = CRC32.calculateToByteArray("helloworld".getBytes());//calculateToByteArray("helloworld".getBytes(),0x04C11DB7L)
// 装填模式
CRC32 crc32 = new CRC32();// new CRC32(0x04C11DB7L);
crc32.update("helloworld1".getBytes());
crc32.update("helloworld2".getBytes());
crc32.getValue();
crc32.getValueToByteArray();
```