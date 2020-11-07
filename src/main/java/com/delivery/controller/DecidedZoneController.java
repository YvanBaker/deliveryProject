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
     * @param ids 定区ids
     * @return
     */
    @RequestMapping(value = "/delecte", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String delecteDecidedZone(String ids) {
        String[] splitId = ids.split(",");
            Boolean b = true;//有关联
        for (int i = 0; i < splitId.length; i++) {
            if (decidedzoneService.assignOrdersIsNo(splitId[i])) {//如果true 表示没有关联 就改b值为
                b = false;
            }
        }
        if (b) {//有关联不能删除
            return JSON.toJSONString("删除失败,有取派员正在派单!");
        }else {
            for (int i = 0; i < splitId.length; i++) {
                decidedzoneService.delectDecidedzone(splitId[i]);
            }
            return JSON.toJSONString("删除成功");
        }
    }

    /**
     * 显示所有关联定区
     * 根据定区id和所属公司查询(包含分页)
     *
     * @return
     */
    @RequestMapping(value = "/decidedZoneShow", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String decidedZoneShow(int page, int rows, String name, String station) {
        PageUtil decidedzones = decidedzoneService.getDecidedZoneShow(page, rows, name, station);
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
     * 关联区域(查找）
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
     * 添加和修改定区 定区名字去重 有传selectId就是修改 没有传selectId就是添加
     *
     * @param areaId      地区id
     * @param staffId     取派员id
     * @param decidedName 定区名称
     * @param selectId    选中定区id(可空)
     * @return
     */
    @RequestMapping(value = "/addDecided", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String addDecided( String decidedName, String staffId,String areaId, String selectId) {
        //名字判重
        Decidedzone decidedzone = decidedzoneService.getDecidedZone(decidedName);
        String dId = "";
        if (decidedzone != null) {
            dId = String.valueOf(decidedzone.getId());
            if (!dId.equals(selectId)) {//如果相同说明在修改时，没修改名字,不相等时就需要判断名字是否存在
                return JSON.toJSONString(0);//名字存在
            }
        }
        if (selectId == "") {//空为添加
            decidedzoneService.addDecidedzone(decidedName, staffId,areaId );
            return JSON.toJSONString(1);//添加
        } else {//判断自身名称是否改变，如果没改变
            if (decidedzoneService.assignOrdersIsNo(selectId)) {//如果true 表示没有关联 可以修改
                decidedzoneService.changDecidedzone(decidedName, staffId, areaId, selectId);
                return JSON.toJSONString(2);//修改
            }else {
                return JSON.toJSONString(3);//有关联,修改失败，
            }

        }
    }

    /**
     * 提交订单关联定区
     *
     * @param OrderIds
     * @return
     */
    @RequestMapping("/assignOrderToDecidedzone")
    public String assignOrdersToDecidedzone(String id, String OrderIds, Model model) {
        //分区id----订单ids
        boolean flog = staffOrderService.addAssignOrders(id, OrderIds);
        model.addAttribute("msg", "关联成功");
        return "base/decidedzone";
    }
}
