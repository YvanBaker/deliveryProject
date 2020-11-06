package com.delivery.interceptor;

import com.delivery.annotaions.CustomerToken;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Yvan
 * @Description CustomerToken 的拦截器
 * @Classname CustomerTokenInterceptor
 * @Date 2020/10/31 17:57
 */
public class CustomerTokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        CustomerToken annotation = method.getAnnotation(CustomerToken.class);
        if (annotation != null) {
            System.out.println("碰到拦截器");
            return true;
        }
        return super.preHandle(request, response, handler);
    }
}
