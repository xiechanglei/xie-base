package io.github.xiechanglei.base.rbac.token;

import org.springframework.lang.NonNull;
import io.github.xiechanglei.base.rbac.properties.RbacConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token发现机制的默认实现,必须存在一个TokenInterceptor的实现类，否则无法实现token的权限过滤
 */
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "io.github.xiechanglei.base.rbac", name = "enable", havingValue = "true", matchIfMissing = true)
public class TokenInterceptor implements HandlerInterceptor, WebMvcConfigurer {
    private final RbacConfigProperties rbacConfigProperties;
    public static final String REQUEST_ATTR_TOKEN_KEY = "BASE_AUTH_TOKEN_INFO";// 存放在request中的token信息的key

    /**
     * 从请求头中获取token，解析token，获取用户信息，将用户信息放入上下文
     */
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        TokenInfo decode = TokenHandler.decode(getTokenStrFromRequest(request));
        request.setAttribute(REQUEST_ATTR_TOKEN_KEY, decode);
        return true;
    }

    public String getTokenStrFromRequest(HttpServletRequest request) {
        String REQUEST_KEY = rbacConfigProperties.getTokenname();
        String authTokenStr = request.getParameter(REQUEST_KEY);
        if (!StringUtils.hasText(authTokenStr)) {
            authTokenStr = request.getHeader(REQUEST_KEY);
        }
        if (!StringUtils.hasText(authTokenStr)) {
            javax.servlet.http.Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (javax.servlet.http.Cookie cookie : cookies) {
                    if (REQUEST_KEY.equals(cookie.getName())) {
                        authTokenStr = cookie.getValue();
                        break;
                    }
                }
            }
        }
        return authTokenStr;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this).addPathPatterns("/**");
    }

}
