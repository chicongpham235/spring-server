package com.map_properties.spring_server.config.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class PermissionsInterceptorConfig implements WebMvcConfigurer {
    private final PermissionsInterceptor permissionsInterceptor;

    @Autowired
    public PermissionsInterceptorConfig(PermissionsInterceptor permissionsInterceptor) {
        this.permissionsInterceptor = permissionsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
                permissionsInterceptor)
                .order(Ordered.LOWEST_PRECEDENCE);
    }
}
