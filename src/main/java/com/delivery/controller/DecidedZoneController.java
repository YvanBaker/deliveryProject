package com.delivery.controller;


import com.alibaba.fastjson.JSON;
import com.delivery.entity.*;
import com.delivery.service.DecidedzoneService;
import com.delivery.util.PageUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class DecidedZoneController {

    @Resource
    DecidedzoneService decidedzoneService;

    /**
     * 管理定区/调度页面
     *
     * @return
     */
    @RequestMapping("/decidedzoneView")
    public String decidedView() {
        return "base/decidedzone";
    }


    /**
     * 查询所有分区
     *
     * @return
     */
    @RequestMapping(value = "/decidedzoneSubarea", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String decidedzoneSubarea() {
        List<Subarea> subareas = decidedzoneService.findAllSubarea();
        return JSON.toJSONString(subareas);
    }



   /* @RequestMapping(value = "/decidedZoneShow", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String decidedZoneShow() {
        List<Decidedzone> decidedzones = decidedzoneService.getDecidedZoneAll();
        System.out.println("decidedzones = " + decidedzones.get(0));
        return JSON.toJSONString(decidedzones);
    }*/
    /**
     * 显示所有关联定区
     * @return
     */
    @RequestMapping(value = "/decidedZoneShow", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String decidedZoneShow(int page,int rows) {
        PageUtil decidedzones = decidedzoneService.getDecidedZoneAll(page,rows);
        return JSON.toJSONString(decidedzones);
    }

    /**
     * 添加定区
     *
     * @return
     */
    /*@RequestMapping(value = "/addDecidedZone",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String addDecidedZone(String ids, Decidedzone decidedzone, String staff_id) {
        decidedzone.setSubarea_ids(ids);
        Staff staff = new Staff();
        staff.setId(Integer.parseInt(staff_id));
        decidedzone.setStaff(staff);
        decidedzoneService.addDecidedzone(decidedzone);
        return JSON.toJSONString("添加成功");
    }*/

    /**
     * 可关联的客户
     *
     * @return
     */
    @RequestMapping("/findAssociationsCustomer")
    @ResponseBody
    public String findAssociationsCustomer() {
        List<Customer> customers = decidedzoneService.getNoAssociationsCustomer();
        return JSON.toJSONString(customers);
    }

    /**
     * 关联区(查找）
     * @return
     */
    /*@RequestMapping("/associationSubarea")
    public String associationSubarea() {
        return JSON.toJSONString("");
    }*/
    @RequestMapping(value = "/regionAjax",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String regionAjax(String q) {
        List<Region>regions= decidedzoneService.associationsRegionAjax(q);
        return JSON.toJSONString(regions);
    }

    /**
     * 添加定区
     *
     * @return
     */
    @RequestMapping(value = "/addDecided", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String addDecided(String areaId, String staffId, String decidedName) {
        boolean b=decidedzoneService.addDecidedzone(areaId,staffId,decidedName);
        /*Staff staff = new Staff();
        staff.setId(Integer.parseInt(staff_id));
        decidedzone.setStaff(staff);
        decidedzoneService.addDecidedzone(decidedzone);*/
        if (b) {
            return "添加成功";
        }else {
            return "添加失败";
        }
    }
    /**
     * 顾客关联定区
     *
     * @param customerIds
     * @return
     */
    @RequestMapping("/assignCustomersToDecidedzone")
    public String assignCustomersToDecidedzone(String customerIds) {
        return "ss";
    }
}
