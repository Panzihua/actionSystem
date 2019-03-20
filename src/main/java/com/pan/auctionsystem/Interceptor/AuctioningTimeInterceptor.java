package com.pan.auctionsystem.Interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import java.util.Map;

public class AuctioningTimeInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier("redisTemplate")
    @Getter @Setter
    private RedisTemplate template;

    @Autowired
    @Getter @Setter
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println(request);
//        RequestWrapper myRequestWrapper = new RequestWrapper(request);
        return false;
    }
}
