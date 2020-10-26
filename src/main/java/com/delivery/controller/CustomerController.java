package com.delivery.controller;

import com.alibaba.fastjson.JSON;
import com.delivery.entity.Customer;
import com.delivery.exception.customer.CustomerAttributesNullException;
import com.delivery.exception.customer.CustomerNameRepeatException;
import com.delivery.exception.customer.CustomerNullException;
import com.delivery.model.MsgResponse;
import com.delivery.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author Yvan
 * @Description 用户的控制层
 * @Classname CustomerController
 * @Date 2020/10/26 11:14
 */
@Controller
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PostMapping(value = "/register")
    @ResponseBody
    public String saveCustomer(Customer customer, HttpSession session) {
        try {
            customerService.saveCustomer(customer);
            session.setAttribute("customer", customer);
        } catch (CustomerNameRepeatException | CustomerAttributesNullException e) {
            return JSON.toJSONString(MsgResponse.buildError(e.getMessage()));
        }
        return JSON.toJSONString(customer);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String loginCustomer(String name, String password, HttpSession session) {
        Customer customer;
        try {
            customer = customerService.loginCustomer(name, password);
            session.setAttribute("customer", customer);
        } catch (CustomerAttributesNullException | CustomerNullException e) {
            return JSON.toJSONString(MsgResponse.buildError(e.getMessage()));
        }
        return JSON.toJSONString(customer);
    }
}
