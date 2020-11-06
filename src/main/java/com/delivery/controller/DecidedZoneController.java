package com.delivery.controller;


import com.alibaba.fastjson.JSON;
import com.delivery.entity.*;
import com.delivery.service.DecidedzoneService;
import com.delivery.service.StaffOrderService;
import com.delivery.service.WorkorderService;
import com.delivery.util.PageUtil;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
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

    @Resource
    WorkorderService workorderService;

    @Resource
    StaffOrderService staffOrderService;

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
     * 删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delecte", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String delecteDecidedZone(String ids) {
        String[] splitId = ids.split(",");
        for (int i = 0; i < splitId.length; i++) {
            decidedzoneService.delectDecidedzone(splitId[i]);
        }
        return JSON.toJSONString("删除成功");
    }

    /**
     * 显示所有关联定区
     *
     * @return
     */
    @RequestMapping(value = "/decidedZoneShow", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String decidedZoneShow(int page, int rows, String id, String station) {
        PageUtil decidedzones = null;
        if ((id != "" && id != null) || (station != "" && station != null)) {
            int did = 0;
            if (station != "" && station != null) {
                station = "%" + station + "%";
            }
            if (id != "" && id != null) {
                did = Integer.parseInt(id);
            }
            decidedzones = decidedzoneService.getDecidedZoneDim(page, rows, did, station);
        } else {
            decidedzones = decidedzoneService.getDecidedZoneAll(page, rows);
        }
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
     * 关联区(查找）
     *
     * @return
     */
    @RequestMapping(value = "/regionAjax", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String regionAjax(String q) {
        List<Region> regions = decidedzoneService.associationsRegionAjax(q);
        return JSON.toJSONString(regions);
    }

    /**
     * 添加定区
     *
     * @return
     */
    @RequestMapping(value = "/addDecided", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String addDecided(String areaId, String staffId, String decidedName, String selectId) {
        //名字判重
        Decidedzone decidedzone = decidedzoneService.getDecidedZone(decidedName);
        String dId = "";
        if (decidedzone != null) {
            dId = String.valueOf(decidedzone.getId());
            if (!dId.equals(selectId)) {//如果相同说明在修改时，没修改名字,不相等时就需要判断名字是否存在
                return JSON.toJSONString(0);//名字存在
            }
        }
        if (selectId == "") {
            decidedzoneService.addDecidedzone(areaId, staffId, decidedName);
            return JSON.toJSONString(1);//添加*******不要加双引号**************
        } else {//判断自身名称是否改变，如果没改变
            decidedzoneService.changDecidedzone(areaId, staffId, decidedName, selectId);
            return JSON.toJSONString(2);//修改
        }
    }

    /**
     * 提交订单关联定区
     *
     * @param OrderIds
     * @return
     */
    @RequestMapping(value = "/assignOrderToDecidedzone", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String assignOrdersToDecidedzone(String id, String OrderIds) {
        //分区id----订单ids
        boolean flog = staffOrderService.addAssignOrders(id, OrderIds);
        return JSON.toJSONString("添加成功");
    }
}
