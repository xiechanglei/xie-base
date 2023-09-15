package com.xiechanglei.code.base.web.resolver;

import com.xiechanglei.code.base.web.config.properties.BaseWebConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class PageTypeResolver implements HandlerMethodArgumentResolver {
    private final BaseWebConfigProperties baseWebConfigProperties;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Integer resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String parameterName = parameter.getParameterName();
        if (parameterName == null) {
            return null;
        }
        String str = webRequest.getParameter(parameterName);
        if (str == null) {
            if (parameterName.equals("page")) {
                return baseWebConfigProperties.getPage();
            }
            if (parameterName.equals("size")) {
                return baseWebConfigProperties.getSize();
            }
            return null;
        }
        return Integer.parseInt(str);
    }
}
