package com.github.aelmod.ssn2.security;

//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class Conf extends WebMvcConfigurationSupport {
//    @Override
//    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(new CurrentUserResolver());
//    }
//}
@Configuration
public class Conf {
    @Bean
    public JwtAuthHelper jwtAuthHelper() {
        return new JwtAuthHelper();
    }
}