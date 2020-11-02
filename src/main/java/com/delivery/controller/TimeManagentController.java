package com.delivery.controller;

import com.alibaba.fastjson.JSONObject;
import com.delivery.entity.TimeManagement;
import com.delivery.service.TimeManagementService;
import com.delivery.util.PageUtil;
import com.delivery.util.TimeUtil;
import com.delivery.utilentity.FindTime;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import java.text.ParseException;

/**
 * Created by LEO15 on 2020/11/2.
 */
@Controller
@RequestMapping("/sys")
public class TimeManagentController {

    @Resource
    private TimeManagementService timeManagementService;

    @RequestMapping("/TimeView")
    public String standardView() {
        return "base/setTime";
    }

    @RequestMapping(value = "/findTimeView" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String findStandardByElements(FindTime findTime){
        PageUtil pageUtil =timeManagementService.selectTimeManagement(findTime);
        return JSONObject.toJSONString(pageUtil);
    }

    @RequestMapping("/addTime")
    public String addTime(TimeManagement timeManagement,Model model) throws ParseException {
        timeManagement.setStatus(1);
        timeManagement.setOperator("admin");
        timeManagement.setoStation("admin");
        timeManagement.setoTime(TimeUtil.localtime());
        boolean flag=timeManagementService.addTimeManagement(timeManagement);
        if (flag) {
            String msg = "添加成功";
            model.addAttribute("msg", msg);
            return "base/setTime";
        } else {
            String msg = "添加失败";
            model.addAttribute("msg", msg);
            return "base/setTime";
        }
    }

    @RequestMapping("/timeDelete")
    public String removeTime(String ids,Model model){
        boolean f=false;
        String[] split=ids.split(",");
        for (String id: split) {
            TimeManagement timeManagement=new TimeManagement();
            timeManagement.setId(Integer.parseInt(id));
            timeManagement.setoTime(TimeUtil.localtime());
            timeManagement.setOperator("admin");
            timeManagement.setoStation("admin");
            f=timeManagementService.removeTimeManagement(timeManagement);
        }
        if (f){
            String msg = "删除成功";
            model.addAttribute("msg", msg);
            return "base/setTime";
        }else {
            String msg = "删除失败";
            model.addAttribute("msg", msg);
            return "base/setTime";
        }
    }

    @RequestMapping("/timeEdit")
    public String updateTime(TimeManagement timeManagement , Model model){
        boolean f=false;
        timeManagement.setoStation("admin");
        timeManagement.setOperator("admin");
        timeManagement.setoTime(TimeUtil.localtime());
        f=timeManagementService.updateTimeManagement(timeManagement);
        if (f){
            String msg="修改时间信息成功";
            model.addAttribute("msg",msg);
            return "base/setTime";
        }else {
            String msg="修改时间失败";
            model.addAttribute("msg",msg);
            return "base/setTime";
        }
    }



}
