package com.delivery.interceptor;

import com.alibaba.fastjson.JSON;
import com.delivery.annotaions.Staff;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author Yvan
 * @Description @Staff 拦截器
 * @Classname StaffInterceptor
 * @Date 2020/11/3 9:50
 */
public class StaffInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            HttpSession session = request.getSession();
            Object staff = session.getAttribute("staff");
            if (staff != null && !method.isAnnotationPresent(Staff.class)) {
                PrintWriter out = response.getWriter();
                response.setContentType("application/json;charset=UTF-8");
                out.write(JSON.toJSONString("权限不足，请联系有权限的人员"));
                return false;
            }
        }
        return true;
    }
}
