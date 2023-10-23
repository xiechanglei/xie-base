package com.xiechanglei.code.base.rbac.controller;

/**
 * 在不同的项目中，消息返回的格式并不相同，所以这类定义一个接口，用于规定消息返回的格式
 * 子项目如果实现了这个接口，并且将实现类注入到spring容器中，那么就可以使用默认的一些内置接口，否则需要自己实现
 */
public interface InternalControllerMessageBuilder {
    Object success(Object data);

    Object failed(String message);

    Object permissionDenied();
}
