package com.delivery.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.delivery.entity.*;
import com.delivery.service.*;
import com.delivery.util.CutAddressUtil;
import com.delivery.utilentity.CustomerAndAddress;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys")
public class Noticebill_add {
    @Resource
    RegionService regionService;
    @Resource
    RegionArgService regionArgService;

    @Resource
    BusinessNoteService businessNoteService;

    /**
     * 页面跳转
     *
     * @return
     */
    @RequestMapping("/noticebill_addView")
    public String noticebill_addView() {
        return "qupai/noticebill_add";
    }

    /**
     * 添加订单
     *
     * @param businessNote
     * @return
     */
    @RequestMapping("/noticebillAdd")
    public String noticebillAdd(BusinessNote businessNote, RegionParam regionParam, Model model) {
        Region address = regionService.findRegionByAreaId(regionParam.getAddressArea());
        Region arrive = regionService.findRegionByAreaId(regionParam.getArriveCityArea());
        businessNote.setAddress(address.getName() + regionParam.getAddressSuffix());
        businessNote.setArriveCity(arrive.getName() + regionParam.getArriveCitySuffix());
        businessNoteService.addbusinessNote(businessNote);
        model.addAttribute("msg", "添加成功");
        return noticebill_addView();
    }

    @RequestMapping(value = "/RegionOne", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String RegionOne() {
        // 读取地区代表
        List<RegionArg> lr = regionArgService.getList();
        lr.forEach(System.out::println);
        // 返回JSON格式的数据
        String json = JSONArray.toJSONString(lr);
        return json;
    }

    @RequestMapping(value = "/RegionTow", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String RegionTow(@RequestParam String parentid) {
        if (parentid != null && !"".equals(parentid)) {
            // 读取地区代表
            List<RegionArg> lr = regionArgService.getCityList(parentid);
            lr.forEach(System.out::println);
            // 返回JSON格式的数据
            String json = JSONArray.toJSONString(lr);
            return json;
        } else {
            return "[]";
        }
    }

    @RequestMapping(value = "/RegionTree", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String RegionTree(@RequestParam String parentid) {
        if (parentid != null && !"".equals(parentid)) {
            // 读取地区代表
            List<RegionArg> lr = regionArgService.getAreaList(parentid);
            // 返回JSON格式的数据
            String json = JSONArray.toJSONString(lr);
            return json;
        } else {
            return "[]";
        }
    }

    /**
     * 双击查看关联订单
     */
    @RequestMapping(value = "/findAssociationsOrderOnDbl", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String findAssociationsOrderOnDbl(String id) {
        List<BusinessNote> businessNotes = businessNoteService.AssociationsOrderOnDbl(id);
        return JSON.toJSONString(businessNotes);
    }

    /**
     * 可关联的订单
     *
     * @return
     */
   /* @RequestMapping(value = "/findAssociationsCustomer",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String findAssociationsCustomer() {
        List<Customer> customers = decidedzoneService.getNoAssociationsCustomer();
        return JSON.toJSONString(customers);
    }*/
    @RequestMapping(value = "/findAssociationsOrder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String findAssociationsOrder() {
        List<BusinessNote> workorOrder = businessNoteService.getNoAssociationsOrder();
        return JSON.toJSONString(workorOrder);
    }


    /**
     * 已关联的订单
     *
     * @return
     */
    @RequestMapping(value = "/findHasAssociationsOrder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String findHasAssociationsOrder(String id) {
        List<BusinessNote> businessNotes = businessNoteService.getHasAssociationsOrder(id);
        return JSON.toJSONString(businessNotes);
    }

    /**
     * 手机号输入查询客户
     *
     * @param clientPhone
     */
   /* @RequestMapping("/billVerify")
    public void billVerify(String clientPhone, HttpServletResponse response) throws IOException {
        Customer customer = customerService.findCustomerByTelephone(clientPhone);
        List<CustomerAddress> customerAddress = customerAddressService.getAddressByUserId(customer.getId());
        response.getWriter().write(JSONObject.toJSONString(new CustomerAndAddress(customer, customerAddress)));
    }*/
}
