package com.delivery.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class Decidedzone {

    @RequestMapping("/decidedzoneView")
    public String decidedView() {
        return "base/decidedzone";
    }


}
