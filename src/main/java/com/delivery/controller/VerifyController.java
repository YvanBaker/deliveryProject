package com.delivery.controller;

import com.alibaba.fastjson.JSON;
import com.delivery.entity.Decidedzone;
import com.delivery.service.DecidedzoneService;
import com.delivery.service.VerifyService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author wenJ
 * @Description 验证数据
 * @Classname VerifyController
 * @Date 2020/11/3 17:00
 */
@Controller
@RequestMapping("/verify")
public class VerifyController {

    @Resource
    VerifyService verifyService;

    /**
     * 验证区域名
     * @param deciname
     * @return
     */
    @RequestMapping(value = "/deciname",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String verifyDeciName(String deciname) {
        Decidedzone decidedzone=verifyService.getDecidedZone(deciname);
        if (decidedzone == null) {
            return JSON.toJSONString("N");
        }else {
            return JSON.toJSONString("Y");
        }
    }
}
