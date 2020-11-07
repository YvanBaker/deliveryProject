package com.delivery.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.delivery.entity.BusinessNote;
import com.delivery.entity.Customer;
import com.delivery.entity.CustomerAddress;
import com.delivery.entity.WorkBill;
import com.delivery.service.AddressService;
import com.delivery.service.BusinessNoteService;
import com.delivery.service.CustomerService;
import com.delivery.utilentity.CustomerAndAddress;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Rollback;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class Noticebill {
    @Resource
    CustomerService customerService;

    @Resource
    AddressService customerAddressService;


    @Resource
    BusinessNoteService businessNoteService;

    @RequestMapping("/noticebillView")
    public String noticebillView() {
        return "qupai/noticebill";
    }

    /**
     * 手机号输入查询客户
     *
     * @param clientPhone
     */
    @RequestMapping("/billVerify")
    public void billVerify(String clientPhone, HttpServletResponse response) throws IOException {
        Customer customer = customerService.queryCustomerByPhone(clientPhone);
        if (customer == null) {
            response.getWriter().write("");
        }else {
            List<CustomerAddress> customerAddress = customerAddressService.getAddressByUserId(customer.getId());
            response.getWriter().write(JSONObject.toJSONString(new CustomerAndAddress(customer, customerAddress)));
        }
    }


    /*@RequestMapping("/noticebillAdd")
    public String noticebillAdd(@ModelAttribute BusinessNote businessNote, Model model) {
        businessNoteService.addbusinessNote(businessNote);
        model.addAttribute("添加成功");
            return "qupai/noticebill_add";
    }*/

}
