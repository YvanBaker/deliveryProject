package com.delivery.controller;

import com.alibaba.fastjson.JSON;
import com.delivery.entity.BusinessNote;
import com.delivery.entity.Region;
import com.delivery.entity.Staff;
import com.delivery.service.DiaoduService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class Diaodu {
    @RequestMapping("/diaoduView")
    public String diaoduView() {
        return "qupai/diaodu";
    }

    @Resource
    DiaoduService diaoduService;

    /**
     * 人员调度
     *
     * @param q
     * @return
     */
    @RequestMapping(value = "/staffAjax", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String diaoduAjax(String q) {
        List<Staff> staffs = diaoduService.associationsStaffAjax(q);
        return JSON.toJSONString(staffs);
    }

    /**
     * 查询未关联的订单
     *
     * @return
     */
    @RequestMapping(value = "/noticebillFindNoassociations", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String noticebillFindNoassociations() {
        List<BusinessNote> businessNotes = diaoduService.findBusinessNote();
        return JSON.toJSONString(businessNotes);
    }

    /**
     * 提交关联，生成工单
     * @param OrderId
     * @param StaffId
     * @return
     */
    @RequestMapping(value = "/diaoduForm",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String diaoduForm(String OrderId,String StaffId) {
        diaoduService.diaoduOrderAssociationsStaff(OrderId, StaffId);
        return "关联成功";
    }
}
