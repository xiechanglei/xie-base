package com.xiechanglei.code.base.rbac.annotation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthAction {
    private final String actionCode;
    private final String actionName;
    private final AuthMenu authMenu;
}
