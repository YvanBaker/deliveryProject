package com.delivery.controller;

import com.alibaba.fastjson.JSON;
import com.delivery.entity.Customer;
import com.delivery.exception.customer.CustomerAttributesNullException;
import com.delivery.exception.customer.CustomerNameRepeatException;
import com.delivery.exception.customer.CustomerNullException;
import com.delivery.model.MsgResponse;
import com.delivery.service.CustomerService;
import com.delivery.util.JwtUtils;
import com.delivery.util.MailUtils;
import com.delivery.util.RandomUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    @CrossOrigin
    public String loginCustomer(String name, String password) {
        Customer customer;
        Map<String, Object> data = new ConcurrentHashMap<>(2);
        try {
            customer = customerService.loginCustomer(name, password);
            String token = JwtUtils.geneJsonWebToken(customer);
            data.put("user", customer);
            data.put("token", token);
        } catch (CustomerAttributesNullException | CustomerNullException e) {
            return JSON.toJSONString(MsgResponse.buildError(e.getMessage()));
        }

        return JSON.toJSONString(MsgResponse.buildSuccess("success", data));
    }

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @CrossOrigin
    public String test(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        return JSON.toJSONString(cookies);
    }

    @GetMapping(value = "/getCode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @CrossOrigin
    public String getCode(String email) {
        String code = RandomUtils.getCode(6, 10);
        String text = "您的验证码是:" + code;
        try {
            MailUtils.sendMail(email, text, MailUtils.CODE_TITLE);
        } catch (Exception e) {
            return JSON.toJSONString(MsgResponse.buildError(e.getMessage()));
        }
        return JSON.toJSONString(MsgResponse.buildSuccess(code));
    }
}
