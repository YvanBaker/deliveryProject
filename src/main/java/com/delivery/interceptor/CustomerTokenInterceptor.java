package com.delivery.interceptor;

import com.delivery.annotaions.CustomerToken;
import com.delivery.exception.utils.CheckJWTException;
import com.delivery.util.JwtUtils;
import com.delivery.util.SendMsgUtil;
import io.jsonwebtoken.Claims;
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
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            CustomerToken annotation = method.getAnnotation(CustomerToken.class);
            if (annotation != null) {
                String token = request.getHeader("token");
                if (token == null) {
                    SendMsgUtil.sendJsonMessage(response, "用户未登录！！");
                    return false;
                }
                Claims claims = null;
                try {
                    claims = JwtUtils.checkJWT(token);
                } catch (CheckJWTException e) {
                    SendMsgUtil.sendJsonMessage(response, "用户登录过期！！");
                    return false;
                }
                return true;
            }
        }
        return super.preHandle(request, response, handler);
    }
}
