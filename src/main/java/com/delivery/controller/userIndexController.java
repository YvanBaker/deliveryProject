package com.delivery.controller;

import com.alibaba.fastjson.JSON;
import com.delivery.entity.User;
import com.delivery.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sys")
public class userIndexController {
    @Resource
    UserService userService;
    @RequestMapping("/userIndex")
    public String userIndex() {
        return "system/userIndex";
    }

    /**
     * 用户显示列表页面
     *
     * @return
     */
    @RequestMapping("/userlist")
    public String userlist() {
        return "system/userlist";
    }

   /* @RequestMapping("/userShow")
    public void userShow() {
        PageInfo<User> userAll = userService.findUserAll();
    }*/
    @GetMapping(value = "/userShow", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String findUser(){
        PageInfo<User> userAll = userService.findUserAll();
        System.out.println("userAll aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa= " + userAll);
        return JSON.toJSONString(userAll);
    }
}
