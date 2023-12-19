# xie-base-common-jpa JPA 模块

`xie-base-common-jpa` 模块是 `xie-base` 项目的子模块，主要用于提供一些通用的JPA工具类。

引入依赖

```xml
<dependency>
    <groupId>io.github.xiechanglei</groupId>
    <artifactId>xie-base-common-jpa</artifactId>
    <version>2.7.17.4</version>
</dependency>
```

### UUIDIdEntity

`UUIDIdEntity` 是一个抽象类，提供了一个 `id` 字段，类型为 `String`，并且使用 `UUID` 作为主键生成策略。

```java
@Entity
@Table(name = "t_user")
public class User extends UUIDIdEntity {
    ...
}
```

### SnowFlakeIdEntity

`SnowFlakeIdEntity` 是一个抽象类，提供了一个 `id` 字段，类型为 `String`，并且使用雪花算法作为主键生成策略。

```java
@Entity
@Table(name = "t_user")
public class User extends SnowFlakeIdEntity {
    ...
}
```

### UUIDIdAndTimeFieldEntity

`UUIDIdAndTimeFieldEntity` 是一个抽象类，提供了一个 `id` 字段，类型为 `String`，并且使用 `UUID` 作为主键生成策略，同时提供了 `createTime` 和 `updateTime` 字段，类型为 `Date`，并且在创建和更新时自动填充。

```java
@Entity
@Table(name = "t_user")
public class User extends UUIDIdAndTimeFieldEntity {
    ...
}
```

### SnowFlakeIdAndTimeFieldEntity

`SnowFlakeIdAndTimeFieldEntity` 是一个抽象类，提供了一个 `id` 字段，类型为 `String`，并且使用雪花算法作为主键生成策略，同时提供了 `createTime` 和 `updateTime` 字段，类型为 `Date`，并且在创建和更新时自动填充。

```java
@Entity
@Table(name = "t_user")
public class User extends SnowFlakeIdAndTimeFieldEntity {
    ...
}
```