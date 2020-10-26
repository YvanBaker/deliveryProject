package com.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/sys")
public class Noticebill_add {
    @RequestMapping("/noticebill_addView")
    public String noticebill_addView() {
        return "qupai/noticebill_add";
    }
}
