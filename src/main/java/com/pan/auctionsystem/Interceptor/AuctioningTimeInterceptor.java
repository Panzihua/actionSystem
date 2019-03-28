package com.pan.auctionsystem.Interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.temporal.Temporal;

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
        String ip = request.getRemoteAddr();

        Object userId = template.opsForValue().get(ip);

        try {
            if (userId == null) {
                response.sendRedirect("登录页面");
                return false;
            } else {
                return true;
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return false;
    }
}
