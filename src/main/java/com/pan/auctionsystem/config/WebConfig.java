package com.pan.auctionsystem.config;

import com.pan.auctionsystem.Interceptor.AuctioningTimeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//         registry.addInterceptor(new AuctioningTimeInterceptor()).addPathPatterns("/**");
    }
}
