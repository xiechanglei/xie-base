# xie-base-rbac rbac 模块

## 1. 介绍

TODO 重新写说明文档

`xie-base-rbac` 模块提供rbac 权限模型默认配置,是基于spring-boot-starter 的一个脚手架

## 2. 使用

### 2.1. 引入依赖

```xml
<dependency>
    <groupId>io.github.xiechanglei</groupId>
    <artifactId>xie-base-rbac</artifactId>
    <version>2.7.17.4</version>
</dependency>
```

### 2.2 使用方式

#### 2.2.1. 权限细分级别

设计权限细分级别到action级别，所以在所有的controller中，都需要增加一个注解`@Permission`，如下：

```java

/**
 * 获取所有的角色
 */
@RbacAuth(RolePermission.QUERY)
@RequestMapping("/rbac/role/list")
public List<RbacAuthRole> listAllRole() {
    return rbacAuthRoleRepository.findAll();
}
```
InternalAuthCode.ROLE.QUERY为一个全局唯一的字符类型的常量，用于标识具体的操作，比如某个页面的下的功能主要有查询，删除，创建等

RbacAuth 注解可以传递多个操作标识符，用以表示该方法可以被满足任意一个操作标识符权限的用户访问

#### 2.2.2. 权限过滤

模块会拦截所有的请求，并且对请求的方法进行权限过滤，如果用户没有权限访问，则会抛出NoPermissionException的异常

请求需要在请求的参数，头部或者cookie中传递一个名为`auth-token`的参数，该参数的值为一串加密的字符串，内部包含了用户的一些信息

该参数的名称可以自行配置
```properties
io.github.xiechanglei.base.rbac.tokenname=auth-token
```
token的生成方式如下：
```java
String token = TokenHandler.encode(new TokenInfo(...))
```

系统会对所有标注了`@RbacAuth`注解的方法进行权限过滤，从数据库中查询该用户是否拥有该权限，如果没有，则会抛出`NoPermissionException`异常

权限默认过滤级别为action级别，可以修改为menu级别

```properties
io.github.xiechanglei.base.rbac.level=action
```

角色权限数据存储在 rbac_auth_role_ref 表中，字段包括 roleId,authType,menuId,actionId

#### 2.2.3. 自动维护权限项

系统会自动维护权限项，将所有的权限项存储在数据库中，如果数据库中不存在该权限项，则会自动创建,可以关闭该功能

```properties
io.github.xiechanglei.base.rbac.auto=false
```

权限可以使用以下方式进行注册，系统会自动扫描并且入库，反正你也需要全局维护这些常量
```java
@PermissionMenu(code = "rbac:menu:role:manager", title = "角色管理")
public class RolePermission {
    @PermissionAction("角色新增")
    public static final String ADD = "rbac:action:role:add";

    @PermissionAction("角色查询")
    public static final String QUERY = "rbac:action:role:query";

    @PermissionAction("角色删除")
    public static final String DELETE = "rbac:action:role:delete";

    @PermissionAction("角色修改")
    public static final String UPDATE = "rbac:action:role:update";

    @PermissionAction("角色禁用启用")
    public static final String ENABLE = "rbac:action:role:enable";

}

```

#### 2.2.4. 关闭权限过滤

```properties
io.github.xiechanglei.base.rbac.enable=false
```



