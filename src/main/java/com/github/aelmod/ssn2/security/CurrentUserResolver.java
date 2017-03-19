package com.github.aelmod.ssn2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CurrentUserResolver implements HandlerMethodArgumentResolver {

    private RequestMappingHandlerAdapter adapter;

    @Autowired
    public CurrentUserResolver(RequestMappingHandlerAdapter adapter) {
        this.adapter = adapter;
    }

    @PostConstruct
    public void prioritizeCustomArgumentMethodHandlers() {
        List<HandlerMethodArgumentResolver> argumentResolvers =
                new ArrayList<>(adapter.getArgumentResolvers());

        List<HandlerMethodArgumentResolver> customResolvers =
                adapter.getCustomArgumentResolvers();

        if (customResolvers != null) {
            argumentResolvers.removeAll(customResolvers);
            argumentResolvers.addAll(0, customResolvers);
        }
        argumentResolvers.add(0, this);
        adapter.setArgumentResolvers(argumentResolvers);
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Objects.nonNull(methodParameter.getParameterAnnotation(CurrentUser.class));
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
