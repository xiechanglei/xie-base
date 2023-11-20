package com.xiechanglei.code.base.rbac.aop;

import com.xiechanglei.code.base.rbac.annotation.RbacAuth;
import com.xiechanglei.code.base.rbac.exception.NoPermissionException;
import com.xiechanglei.code.base.rbac.properties.RbacConfigProperties;
import com.xiechanglei.code.base.rbac.token.DefaultPermissionService;
import com.xiechanglei.code.base.rbac.token.PermissionService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Aspect
@ConditionalOnProperty(prefix = "com.xiechanglei.code.base.rbac", name = "enable", havingValue = "true", matchIfMissing = true)
public class RbacAuthAop {
    private final PermissionService permissionService;
    private final DefaultPermissionService defaultPermissionService;
    private final RbacConfigProperties rbacConfigProperties;

    public RbacAuthAop(@Autowired(required = false) PermissionService permissionService, DefaultPermissionService defaultPermissionService, RbacConfigProperties rbacConfigProperties) {
        this.permissionService = permissionService;
        this.defaultPermissionService = defaultPermissionService;
        this.rbacConfigProperties = rbacConfigProperties;
    }

    @Around("@annotation(com.xiechanglei.code.base.rbac.annotation.RbacAuth)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        if (rbacConfigProperties.isEnable()) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            String[] value = signature.getMethod().getAnnotation(RbacAuth.class).value();
            boolean hasPermission = permissionService == null ? defaultPermissionService.hasPermission(value) : permissionService.hasPermission(value);
            if (!hasPermission) {
                throw new NoPermissionException();
            }
        }
        return joinPoint.proceed();
    }

}