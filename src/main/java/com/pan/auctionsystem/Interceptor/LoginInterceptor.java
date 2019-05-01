package com.pan.auctionsystem.Interceptor;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate template;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String ip = request.getRemoteAddr();

        String userId = template.opsForValue().get("ip_" + ip);

        try {
            if (userId == null) {
                response.sendRedirect("/Login.html");
                return false;
            } else {
                return true;
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        template.expire("ip_" + request.getRemoteAddr(), 1, TimeUnit.HOURS);
    }
}
