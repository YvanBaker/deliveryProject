package com.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class DivideArea {

    @RequestMapping("/divideAreaView")
    public String divideArea() {
        return "base/divideArea";
    }
}
