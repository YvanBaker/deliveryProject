package com.delivery.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.delivery.entity.User;
import com.delivery.service.UserService;
import com.delivery.util.JwtUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yvan
 * @Description 用户控制层
 * @Classname UserController
 * @Date 2020/10/18 18:35
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/save")
    @ResponseBody
    public String saveUser(User user, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        user = userService.saveUser(user);
        String token = JwtUtils.geneJsonWebToken(user);
        Cookie cookie = new Cookie("user", token);
        response.addCookie(cookie);
        return JSONObject.toJSONString(user);
    }

    @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String findUser(HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + ":" + cookie.getValue());
        }
        PageInfo<User> userAll = userService.findUserAll();
        String token = JwtUtils.geneJsonWebToken(userAll.getList().get(0));
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);
        return JSON.toJSONString(userAll);
    }

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String test(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        return JSON.toJSONString(cookies);
    }

}
