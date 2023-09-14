package com.xiechanglei.code.base.rbac.annotation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthMenu {
    private final String menuId;
    private final String menuName;
}