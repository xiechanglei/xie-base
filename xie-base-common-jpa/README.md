# xie-base-common-jpa JPA 模块

## 1. 介绍

`xie-base-common-jpa` 模块是 `xie-base` 项目的子模块，主要用于提供一些通用的JPA工具类。

## 2. 使用

### 2.1. 引入依赖

```xml
<dependency>
    <groupId>com.xiechanglei.code</groupId>
    <artifactId>xie-base-common-jpa</artifactId>
    <version>${xie-base.version}</version>
</dependency>
```

### 2.2. 使用示例

#### 2.2.1. `com.xiechanglei.code.base.common.jpa.entity.UUIDIdEntity`

`UUIDIdEntity` 是一个抽象类，提供了一个 `id` 字段，类型为 `String`，并且使用 `UUID` 作为主键生成策略。

```java
@Entity
@Table(name = "t_user")
public class User extends UUIDIdEntity {
    ...
}
```

#### 2.2.2. `com.xiechanglei.code.base.common.jpa.entity.SnowFlakeIdEntity`

`SnowFlakeIdEntity` 是一个抽象类，提供了一个 `id` 字段，类型为 `String`，并且使用雪花算法作为主键生成策略。

```java
@Entity
@Table(name = "t_user")
public class User extends SnowFlakeIdEntity {
    ...
}
```

#### 2.2.3. `com.xiechanglei.code.base.common.jpa.entity.UUIDIdAndTimeFieldEntity`

`UUIDIdAndTimeFieldEntity` 是一个抽象类，提供了一个 `id` 字段，类型为 `String`，并且使用 `UUID` 作为主键生成策略，同时提供了 `createTime` 和 `updateTime` 字段，类型为 `Date`，并且在创建和更新时自动填充。

```java
@Entity
@Table(name = "t_user")
public class User extends UUIDIdAndTimeFieldEntity {
    ...
}
```

#### 2.2.4. `com.xiechanglei.code.base.common.jpa.entity.SnowFlakeIdAndTimeFieldEntity`

`SnowFlakeIdAndTimeFieldEntity` 是一个抽象类，提供了一个 `id` 字段，类型为 `String`，并且使用雪花算法作为主键生成策略，同时提供了 `createTime` 和 `updateTime` 字段，类型为 `Date`，并且在创建和更新时自动填充。

```java
@Entity
@Table(name = "t_user")
public class User extends SnowFlakeIdAndTimeFieldEntity {
    ...
}
```

#### 2.2.5. `com.xiechanglei.code.base.common.jpa.entity.BaseEntity`

`BaseEntity` 是一个接口，提供了两个方法，一个是`pure`，将对象的id，createTime，updateTime设置为null,使他变成一个纯粹的业务数据承载实体，防止前端传递一些不可覆盖的数据

另一个是`fork`，用于从数据库中获取旧的对象，并且fork到自己身上，可以完成更新的功能,如果需要自定义一些字段是不可更新的，那么可以在对应的属性上增加@NoOverwrite注解，这样，fork的时候就会忽略这些属性

```java
@Entity
@Table(name = "t_user")
public class User implements BaseEntity<User> {
    ...
}
```