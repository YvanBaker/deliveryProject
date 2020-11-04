package com.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class roleController {
    /**
     * 角色管理页面
     * @return
     */
    @RequestMapping("/roleManager")
    public String roleManager() {
        return "system/role";
    }

    /**
     * 添加角色页面
     * @return
     */
    @RequestMapping("/role_addShow")
    public String role_addShow() {
        return "system/role_add";
    }
}
