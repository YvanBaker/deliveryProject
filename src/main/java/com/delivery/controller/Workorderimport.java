package com.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class Workorderimport {
    @RequestMapping("/workorderimportView")
    public String workorderimportView() {
        return "qupai/workorderimport";
    }
}
