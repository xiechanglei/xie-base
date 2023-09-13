package com.xiechanglei.code.base.rbac.annotation.aop;

import com.xiechanglei.code.base.rbac.annotation.RbacAuth;
import com.xiechanglei.code.base.rbac.exception.NoPermissionException;
import com.xiechanglei.code.base.rbac.token.DefaultPermissionService;
import com.xiechanglei.code.base.rbac.token.PermissionService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class RbacAuthAop {
    private final PermissionService permissionService;
    private final DefaultPermissionService defaultPermissionService;

    public RbacAuthAop(@Autowired(required = false) PermissionService permissionService, DefaultPermissionService defaultPermissionService) {
        this.permissionService = permissionService;
        this.defaultPermissionService = defaultPermissionService;
    }

    @Around("@annotation(com.xiechanglei.code.base.rbac.annotation.RbacAuth)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] value = signature.getMethod().getAnnotation(RbacAuth.class).value();
        boolean hasPermission = permissionService == null ? defaultPermissionService.hasPermission(Arrays.asList(value)) : permissionService.hasPermission(Arrays.asList(value));
        if (!hasPermission) {
            throw new NoPermissionException();
        }
        return joinPoint.proceed();
    }

}
