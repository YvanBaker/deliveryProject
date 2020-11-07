package com.delivery.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.delivery.entity.*;
import com.delivery.exception.customer.AddressNumberException;
import com.delivery.model.MsgResponse;
import com.delivery.service.AddressService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yvan
 * @Description address 控制层
 * @Classname AddressController
 * @Date 2020/10/21 13:27
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    private final static String SUCCESS = "success";
    private final static String REPEAT = "repeat";

    @Resource
    private AddressService addressService;


    /**
     * 获取 省 json
     *
     * @return String
     */
    @GetMapping(value = "/provinces", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @CrossOrigin
    public String getProvince() {
        List<Provinces> provincesList = addressService.queryProvincesAll();
        MsgResponse msgResponse = MsgResponse.buildSuccess(SUCCESS, provincesList);
        return JSON.toJSONString(msgResponse, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 获取 市 json
     *
     * @param provinceId 省 id
     * @return String
     */
    @GetMapping(value = "/city", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @CrossOrigin
    public String getCity(String provinceId) {
        List<City> cityList = addressService.queryCityByProvinceId(provinceId);
        MsgResponse msgResponse = MsgResponse.buildSuccess(SUCCESS, cityList);
        return JSON.toJSONString(msgResponse, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 获取 区 json
     *
     * @param cityId 市 id
     * @return
     */
    @GetMapping(value = "/areas", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @CrossOrigin
    public String getAreas(String cityId) {
        List<Areas> areasList = addressService.queryAresByCityId(cityId);
        MsgResponse msgResponse = MsgResponse.buildSuccess(SUCCESS, areasList);
        return JSON.toJSONString(msgResponse, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 保存 寄件 地址接口
     *
     * @param customerAddress 用户地址
     * @return String
     */
    @PostMapping(value = "/saveSendAddress", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @CrossOrigin
    public String saveCustomerAddress(CustomerAddress customerAddress) {
        CustomerAddress res = null;
        try {
            res = addressService.saveCustomerAddress(customerAddress);
        } catch (AddressNumberException e) {
            return JSON.toJSONString(MsgResponse.buildError(e.getMessage()));
        }
        MsgResponse msgResponse;
        if (customerAddress.getId() == null || customerAddress.getId() == 0) {
            msgResponse = MsgResponse.buildSuccess(REPEAT, res);
        } else {
            msgResponse = MsgResponse.buildSuccess(SUCCESS, customerAddress);
        }
        return JSON.toJSONString(msgResponse);
    }

    /**
     * 获取用户地址列表 用户 id
     *
     * @param customerId 用户id
     * @return
     */
    @GetMapping(value = "/customerAddress", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @CrossOrigin
    public String getCustomerAddress(@RequestParam("id") int customerId) {
        List<CustomerAddress> customerAddressList = addressService.queryCustomerAddresses(customerId);
        return JSON.toJSONString(MsgResponse.buildSuccess(SUCCESS, customerAddressList),
                SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 保存 收件人 地址接口
     *
     * @param address 地址
     * @return String
     */
    @PostMapping(value = "/saveReceiveAddress", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @CrossOrigin
    public String saveCustomerReceiveAddress(CustomerReceiveAddress address) {
        CustomerReceiveAddress res = null;
        try {
            res = addressService.saveCustomerReceiveAddress(address);
        } catch (AddressNumberException e) {
            return JSON.toJSONString(MsgResponse.buildError(e.getMessage()));
        }
        MsgResponse msgResponse;
        if (address.getId() == null || address.getId() == 0) {
            msgResponse = MsgResponse.buildSuccess(REPEAT, res);
        } else {
            msgResponse = MsgResponse.buildSuccess(SUCCESS, address);
        }
        return JSON.toJSONString(msgResponse);
    }

    @GetMapping(value = "/receiveAddress", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @CrossOrigin
    public String getCustomerReceiveAddress(@RequestParam("id") int customerId) {
        List<CustomerReceiveAddress> customerReceiveAddresses =
                addressService.queryCustomerReceiveAddressesByCustomerId(customerId);
        return JSON.toJSONString(MsgResponse.buildSuccess(SUCCESS, customerReceiveAddresses),
                SerializerFeature.DisableCircularReferenceDetect);
    }
}
