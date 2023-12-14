package com.xiechanglei.code.base.rbac.internal;

import com.xiechanglei.code.base.common.bean.message.ErrorDefinition;

public class ErrorConstant {
    public static final ErrorDefinition ROLE_EXISTS = ErrorDefinition.of(1200001, "角色名称已存在");
    public static final ErrorDefinition ROLE_CAN_NOT_DELETE = ErrorDefinition.of(1200002, "角色下有用户，不能删除");
}
