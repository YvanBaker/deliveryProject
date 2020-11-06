package com.delivery.controller;

import com.alibaba.fastjson.JSONObject;
import com.delivery.entity.Admin;
import com.delivery.entity.Line;
import com.delivery.service.LineService;
import com.delivery.util.PageUtil;
import com.delivery.util.TimeUtil;
import com.delivery.utilentity.FindLine;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



/**
 * TODO
 * Created by LEO15 on 2020/10/29.
 * @author fujianain
 */
@Controller
@RequestMapping("/sys")
public class LineController {
    @Resource
    private LineService lineService;

    @RequestMapping("/lineView")
    public String lineView(){
        return "base/setLine";
    }

    @RequestMapping(value = "/lineShow",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String lineShow(FindLine findLine)throws IOException{
        PageUtil pageUtil=lineService.selectLine(findLine);
        return JSONObject.toJSONString(pageUtil);
    }

    @RequestMapping(value = "/addLine", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String addStandard(Line line, Model model, HttpSession session) {
        Admin admin= (Admin) session.getAttribute("admin");
            boolean flag=false;
            line.setStatus(1);
            line.setOperator(admin.getAdminName());
            line.setoStation(admin.getStations());
            line.setoTime(TimeUtil.localtime());
            flag = lineService.addLine(line);
            if (flag) {
                String msg = "添加成功";
                model.addAttribute("msg", msg);
                return "base/setLine";
            } else {
                String msg = "添加失败";
                model.addAttribute("msg", msg);
                return "base/setLine";
            }
    }

    @RequestMapping("/lineDelete")
    public String removeLine(String ids,Model model){
        boolean flag=false;
        String[] split=ids.split(",");
        for (String id:split){
            Line line=new Line();
            line.setId(Integer.parseInt(id));
            line.setStatus(0);
            line.setoTime(TimeUtil.localtime());
            flag=lineService.removeLine(line);
        }
        if (flag) {
            String msg = "班车删除成功,请及时告知司机";
            model.addAttribute("msg", msg);
            return "base/setLine";
        } else {
            String msg = "班车删除失败";
            model.addAttribute("msg", msg);
            return "base/setLine";
        }
    }

    @RequestMapping("/lineEdit")
    public String updateLine(Line line, Model model,HttpSession session){
        Admin admin= (Admin) session.getAttribute("admin");
        boolean f=false;
        line.setOperator(admin.getAdminName());
        line.setoStation(admin.getStations());
        line.setoTime(TimeUtil.localtime());
        f=lineService.updateLine(line);
        if (f){
            String msg="修改班车信息成功";
            model.addAttribute("msg",msg);
            return "base/setLine";
        }else {
            String msg="修改班车失败";
            model.addAttribute("msg",msg);
            return "base/setLine";
        }
    }


}
