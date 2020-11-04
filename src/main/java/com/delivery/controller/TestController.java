package com.delivery.controller;

import com.alibaba.fastjson.JSON;
import com.delivery.annotaions.Admin;
import com.delivery.annotaions.Staff;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author Yvan
 * @Description 测试Controller
 * @Classname TestController
 * @Date 2020/10/31 17:51
 */
@Controller
@Admin
public class TestController {

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @CrossOrigin
    public String test(HttpServletRequest request) {
        Enumeration<String> token = request.getHeaders("Token");
        Cookie[] cookies = request.getCookies();
        request.getSession().setAttribute("staff", "123456");
        return JSON.toJSONString(token);
    }

    @GetMapping(value = "/test1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @CrossOrigin
    public String test1(HttpServletRequest request) {
        Enumeration<String> token = request.getHeaders("Token");
        Cookie[] cookies = request.getCookies();
        return JSON.toJSONString(token);
    }

    @GetMapping(value = "/test2")
    @ResponseBody
    @Staff
    private String test2(){
        return "11111";
    }
}
