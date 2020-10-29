package com.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sys")
public class powerManagerController {

    @RequestMapping("/powerManager")
    public String powerManager() {
        return "system/function";
    }
}
