package com.delivery.controller;

import com.alibaba.fastjson.JSONObject;
import com.delivery.entity.Customer;
import com.delivery.entity.WorkBill;
import com.delivery.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/sys")
public class Noticebill {
    @Resource
    CustomerService customerService;


    @RequestMapping("/noticebillView")
    public String noticebillView() {
        return "qupai/noticebill";
    }

    /**
     * 验证输入
     * @param telephone
     */
    @RequestMapping("/billVerify")
    public void billVerify(String telephone, HttpServletResponse response) throws IOException {
        Customer customer=customerService.findCustomerByTelephone(telephone);
        response.getWriter().write(JSONObject.toJSONString(customer));
    }
}
