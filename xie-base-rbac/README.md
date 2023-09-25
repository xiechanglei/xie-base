# xie-base-rbac rbac 模块

## 1. 介绍

TODO 重新写说明文档

`xie-base-rbac` 模块提供rbac 权限模型默认配置,

## 2. 使用

### 2.1. 引入依赖

```xml
<dependency>
    <groupId>com.xiechanglei.code</groupId>
    <artifactId>xie-base-rbac</artifactId>
    <version>${xie-base.version}</version>
</dependency>
```

### 2.2 使用方式

#### 2.2.1. 权限细分级别

设计权限细分级别到action级别，所以在所有的controller中，都需要增加一个注解`@Permission`，如下：

```java

@RbacAuth(InternalAuthCode.ROLE.QUERY)
public void test(){
    // do something
}
```
InternalAuthCode.ROLE.QUERY为一个全局唯一的字符类型的常量，用于标识具体的操作，比如某个页面的下的功能主要有查询，删除，创建等

RbacAuth 注解可以传递多个操作标识符，用以表示该方法可以被满足任意一个操作标识符权限的用户访问

#### 2.2.2. 权限过滤

模块会拦截所有的请求，并且对请求的方法进行权限过滤，如果用户没有权限访问，则会抛出NoPermissionException的异常

请求需要在请求的参数，头部或者cookie中传递一个名为`auth-token`的参数，该参数的值为一串加密的字符串，内部包含了用户的一些信息

该参数的名称可以自行配置
```properties
com.xiechanglei.code.base.rbac.tokenname=auth-token
```
token的生成方式如下：
```java
String token = TokenHandler.encode(new TokenInfo(...))
```

系统会对所有标注了`@RbacAuth`注解的方法进行权限过滤，从数据库中查询该用户是否拥有该权限，如果没有，则会抛出`NoPermissionException`异常

权限默认过滤级别为action级别，可以修改为menu级别

```properties
com.xiechanglei.code.base.rbac.level=action
```

角色权限数据存储在 rbac_auth_role_ref 表中，字段包括 roleId,authType,menuId,actionId

```properties

#### 2.2.3. 自动维护权限项

系统会自动维护权限项，将所有的权限项存储在数据库中，如果数据库中不存在该权限项，则会自动创建,可以关闭该功能

```properties
com.xiechanglei.code.base.rbac.auto=false
```

权限使用spring boot bean的方式进行注册，反正你也需要全局维护这些常量
```java
@Component
public interface InternalAuthCode {
    @Component
    class ROLE {
        public static final String ADD = "rbac:action:role:add";
        public static final String QUERY = "rbac:action:role:query";
        public static final String EDIT = "rbac:action:role:edit";
        public static final String ENABLE = "rbac:action:role:enable";

        static {
            AuthMenu roleMenu = new AuthMenu("rbac:menu:role:manager", "角色管理");
            AuthCodeManager.register(new AuthAction(ADD, "角色新增", roleMenu));
            AuthCodeManager.register(new AuthAction(QUERY, "角色查询", roleMenu));
            AuthCodeManager.register(new AuthAction(EDIT, "角色编辑", roleMenu));
            AuthCodeManager.register(new AuthAction(ENABLE, "角色启用/禁用", roleMenu));
        }
    }
}
```

#### 2.2.4. 关闭权限过滤

```properties
com.xiechanglei.code.base.rbac.enable=false
```



