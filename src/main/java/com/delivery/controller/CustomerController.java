package com.delivery.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.delivery.annotaions.CustomerToken;
import com.delivery.entity.Customer;
import com.delivery.entity.CustomerWorkOrder;
import com.delivery.exception.customer.CustomerAttributesNullException;
import com.delivery.exception.customer.CustomerNameRepeatException;
import com.delivery.exception.customer.CustomerNullException;
import com.delivery.model.CustomerWorkOrderInfo;
import com.delivery.model.MsgResponse;
import com.delivery.model.PickupAddress;
import com.delivery.service.AddressService;
import com.delivery.service.CustomerService;
import com.delivery.service.CustomerWorkOrderService;
import com.delivery.util.JwtUtils;
import com.delivery.util.MailUtils;
import com.delivery.util.RandomUtils;
import io.jsonwebtoken.Claims;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yvan
 * @Description 用户的控制层
 * @Classname CustomerController
 * @Date 2020/10/26 11:14
 */
@Controller
@ResponseBody
public class CustomerController {

    private final String SUCCESS = "success";
    private final String ERROR = "error";

    @Resource
    private CustomerService customerService;

    @Resource
    private AddressService addressService;

    @Resource
    private CustomerWorkOrderService customerWorkOrderService;

    @PostMapping(value = "/register")
    @CrossOrigin
    public String saveCustomer(Customer customer, HttpSession session) {
        try {
            customerService.saveCustomer(customer);
        } catch (CustomerNameRepeatException | CustomerAttributesNullException e) {
            return JSON.toJSONString(MsgResponse.buildError(e.getMessage()));
        }
        Map<String, Object> data = new ConcurrentHashMap<>(2);
        String token = JwtUtils.geneJsonWebToken(customer);
        data.put("user", customer);
        data.put("token", token);
        return JSON.toJSONString(MsgResponse.buildSuccess(SUCCESS, data));
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin
    public String loginCustomer(String name, String password) {
        Customer customer;
        Map<String, Object> data = new ConcurrentHashMap<>(2);
        try {
            customer = customerService.loginCustomer(name, password);
        } catch (CustomerAttributesNullException | CustomerNullException e) {
            return JSON.toJSONString(MsgResponse.buildError(e.getMessage()));
        }
        String token = JwtUtils.geneJsonWebToken(customer);
        data.put("user", customer);
        data.put("token", token);
        return JSON.toJSONString(MsgResponse.buildSuccess(SUCCESS, data));
    }

    @GetMapping(value = "/getCode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin
    public String getCode(String email) {
        String code = RandomUtils.getCode(6, 10);
        String text = "您的验证码是: " + code + " ,验证码在 5 分钟中内有效";
        try {
            MailUtils.sendMail(email, text, MailUtils.CODE_TITLE);
        } catch (Exception e) {
            return JSON.toJSONString(MsgResponse.buildError(ERROR));
        }
        String tokeEmail = JwtUtils.geneJsonWebTokeEmail(email);
        return JSON.toJSONString(MsgResponse.buildSuccess(code, tokeEmail));
    }

    @PostMapping(value = "/resetPassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin
    @CustomerToken
    public String resetPassword(String email, String password, HttpServletRequest request) {
        String emailToken = request.getHeader("emailToken");
        Claims claims = null;
        try {
            claims = JwtUtils.checkJWT(emailToken);
        } catch (Exception e) {
            return JSON.toJSONString(MsgResponse.buildError(ERROR, "验证码已经过期！！"));
        }
        String token = (String) claims.get("email");
        if (!email.equals(token)) {
            return JSON.toJSONString(MsgResponse.buildError(ERROR, "非法token！！"));
        }
        boolean flag = customerService.resetPassword(email, password);
        if (!flag) {
            return JSON.toJSONString(
                    MsgResponse.buildError(ERROR, "修改失败，该邮箱可能没有注册！！"));
        }
        return JSON.toJSONString(MsgResponse.buildSuccess(SUCCESS, "修改密码成功！！"));
    }

    @PostMapping(value = "/placeOrder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin
    @CustomerToken
    public String placeOrder(CustomerWorkOrder order) {
        CustomerWorkOrder customerWorkOrder = customerService.saveOrder(order);
        if (customerWorkOrder.getId() != null || customerWorkOrder.getId() == 0) {
            return JSON.toJSONString(MsgResponse.buildSuccess(SUCCESS, customerWorkOrder));
        }
        return JSON.toJSONString(MsgResponse.buildError("下单失败"));
    }

    @GetMapping(value = "/repeatName", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin
    public String nameOfRepeat(String name) {
        Customer customer = customerService.queryCustomerByName(name);
        if (customer != null) {
            return JSON.toJSONString(MsgResponse.buildError("用户名重复！！"));
        }
        return JSON.toJSONString(MsgResponse.buildSuccess(SUCCESS));
    }

    @GetMapping(value = "/repeatPhone", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin
    public String phoneOfRepeat(String phone) {
        Customer customer = customerService.queryCustomerByPhone(phone);
        if (customer != null) {
            return JSON.toJSONString(MsgResponse.buildError("手机号重复！！"));
        }
        return JSON.toJSONString(MsgResponse.buildSuccess(SUCCESS));
    }

    @GetMapping(value = "/repeatEmail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin
    public String emailOfRepeat(String email) {
        Customer customer = customerService.queryCustomerByEmail(email);
        if (customer != null) {
            return JSON.toJSONString(MsgResponse.buildError("邮箱重复！！"));
        }
        return JSON.toJSONString(MsgResponse.buildSuccess(SUCCESS));
    }

    @GetMapping(value = "/addressList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin
    @CustomerToken
    public String getAddressList(int id, HttpServletRequest request) {
        String token = request.getHeader("token");
        Claims claims = JwtUtils.checkJWT(token);
        Integer tokenId = (Integer) claims.get("id");
        if (tokenId != id) {
            return JSON.toJSONString(MsgResponse.buildError("参数不一致"));
        }
        List<PickupAddress> pickupAddresses = customerService.queryPickupsByUserId(id);
        return JSON.toJSONString(MsgResponse.buildSuccess(SUCCESS, pickupAddresses),
                SerializerFeature.DisableCircularReferenceDetect);
    }

    @GetMapping(value = "/receiveAddressList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin
    @CustomerToken
    public String getReceiveAddressList(int id, HttpServletRequest request) {
        String token = request.getHeader("token");
        Claims claims = JwtUtils.checkJWT(token);
        Integer tokenId = (Integer) claims.get("id");
        if (tokenId != id) {
            return JSON.toJSONString(MsgResponse.buildError("参数不一致"));
        }
        List<PickupAddress> receiveAddresses = customerService.queryReceiveByUserId(id);
        return JSON.toJSONString(MsgResponse.buildSuccess(SUCCESS, receiveAddresses),
                SerializerFeature.DisableCircularReferenceDetect);
    }

    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin
    @CustomerToken
    public String generateOrder(CustomerWorkOrder order, HttpServletRequest request) {
        String token = request.getHeader("token");
        Claims claims = null;
        try {
            claims = JwtUtils.checkJWT(token);
        } catch (Exception e) {
            return JSON.toJSONString(MsgResponse.buildError("用户登录过期！！"));
        }
        Integer id = (Integer) claims.get("id");
        order.setCustomerId(id);
        customerWorkOrderService.generateOrder(order);
        if (order.getId() == null || order.getId() == 0) {
            return JSON.toJSONString(MsgResponse.buildError("下单失败！！"));
        }
        return JSON.toJSONString(MsgResponse.buildSuccess("下单成功！！"));
    }

    @GetMapping(value = "/getOrder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin
    @CustomerToken
    public String getOrderId(HttpServletRequest request) {
        String token = request.getHeader("token");
        Claims claims = JwtUtils.checkJWT(token);
        Integer id = (Integer) claims.get("id");
        List<CustomerWorkOrderInfo> list = customerWorkOrderService.queryOrder(id);

        return JSON.toJSONString(MsgResponse.buildSuccess(SUCCESS, list),
                SerializerFeature.DisableCircularReferenceDetect);
    }

    @GetMapping(value = "/customerInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CustomerToken
    @CrossOrigin
    public String getCustomerInfo(int id, HttpServletRequest request) {
        String token = request.getHeader("token");
        Claims claims = JwtUtils.checkJWT(token);
        Integer tokenId = (Integer) claims.get("id");
        if (tokenId == id) {
            Customer customer = customerService.queryCustomerById(id);
            return JSON.toJSONString(MsgResponse.buildSuccess(SUCCESS, customer),
                    SerializerFeature.DisableCheckSpecialChar);
        }
        return JSON.toJSONString(MsgResponse.buildError("参数错误！！"));
    }
}
