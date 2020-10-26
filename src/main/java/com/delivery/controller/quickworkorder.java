package com.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class quickworkorder {


    @RequestMapping("/quickworkorderView")
    public String quickworkorderView() {
        return "qupai/quickworkorder";
    }
}
