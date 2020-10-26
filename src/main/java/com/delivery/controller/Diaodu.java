package com.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class Diaodu {
    @RequestMapping("/diaoduView")
    public String diaoduView() {
        return "qupai/diaodu";
    }
}
