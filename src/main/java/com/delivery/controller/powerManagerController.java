package com.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sys")
public class powerManagerController {

    /**
     * 权限管理页面
     * @return
     */
    @RequestMapping("/powerManager")
    public String powerManager() {
        return "system/function";
    }

    /**
     * 添加权限页面
     * @return
     */
    @RequestMapping("/function_addShow")
    public String function_addShow() {
        return "system/function_add";
    }

}
