package io.github.xiechanglei.base.rbac.token;

import io.github.xiechanglei.base.rbac.properties.RbacConfigProperties;
import io.github.xiechanglei.base.rbac.repo.RbacAuthActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "io.github.xiechanglei.base.rbac", name = "enable", havingValue = "true", matchIfMissing = true)
public class DefaultPermissionService {
    private final RbacAuthActionRepository getRbacAuthActionRepository;

    private final RbacConfigProperties rbacConfigProperties;

    /**
     * 从当前request中获取token中的用户id,查询角色，再查询角色对应的权限,直接从数据库中查询，如果需要可以重写该方法
     */
    public boolean hasPermission(String[] permissions) {
        TokenInfo tokenInfo = (TokenInfo) ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getAttribute(TokenInterceptor.REQUEST_ATTR_TOKEN_KEY);
        if (tokenInfo != null) {
            if (RbacConfigProperties.LEVEL_MENU.equals(rbacConfigProperties.getLevel())) {
                return !getRbacAuthActionRepository.findActionCodeByRoleMenuAction(tokenInfo.getUserId(), permissions).isEmpty();
            }
            return !getRbacAuthActionRepository.findActionCodeByRoleAction(tokenInfo.getUserId(), permissions).isEmpty();
        }
        return false;
    }
}
