package com.delivery.interceptor;

import com.delivery.annotaions.Admin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @author Yvan
 * @Description @Admin 拦截器
 * @Classname AdminInterceptor
 * @Date 2020/11/3 9:29
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> clazz = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(Admin.class)) {
                HttpSession session = request.getSession();
                Object admin = session.getAttribute("admin");
                Object staff = session.getAttribute("staff");
                if (admin == null && staff == null) {
                    request.setAttribute("msg", "请先登录");
                    request.getRequestDispatcher("admin/loginView").forward(request, response);
                    return false;
                }
            } else if (method.isAnnotationPresent(Admin.class)) {
                System.out.println("方法上碰到注解");
                return true;
            }
        }
        return true;
    }
}
