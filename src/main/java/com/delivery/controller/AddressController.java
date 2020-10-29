package com.delivery.controller;

import com.alibaba.fastjson.JSON;
import com.delivery.entity.Areas;
import com.delivery.entity.City;
import com.delivery.entity.CustomerAddress;
import com.delivery.entity.Provinces;
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


    @GetMapping(value = "/provinces", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @CrossOrigin
    public String getProvince() {
        List<Provinces> provincesList = addressService.queryProvincesAll();
        MsgResponse msgResponse = MsgResponse.buildSuccess(SUCCESS, provincesList);
        return JSON.toJSONString(msgResponse);
    }

    @GetMapping(value = "/city", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getCity(String provinceId) {
        List<City> cityList = addressService.queryCityByProvinceId(provinceId);
        MsgResponse msgResponse = MsgResponse.buildSuccess(SUCCESS, cityList);
        return JSON.toJSONString(msgResponse);
    }

    @GetMapping(value = "/areas", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getAreas(String cityId) {
        List<Areas> areasList = addressService.queryAresByCityId(cityId);
        MsgResponse msgResponse = MsgResponse.buildSuccess(SUCCESS, areasList);
        return JSON.toJSONString(msgResponse);
    }

    @PostMapping(value = "/saveSendAddress", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String saveCustomerAddress(CustomerAddress customerAddress) {
        CustomerAddress res = addressService.saveCustomerAddress(customerAddress);
        MsgResponse msgResponse;
        System.out.println(customerAddress.getId());
        if (customerAddress.getId() == 0) {
            msgResponse = MsgResponse.buildSuccess(REPEAT, res);
        } else {
            msgResponse = MsgResponse.buildSuccess(SUCCESS, customerAddress);
        }
        return JSON.toJSONString(msgResponse);
    }


}
