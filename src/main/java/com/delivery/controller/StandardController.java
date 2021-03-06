package com.delivery.controller;

import com.alibaba.fastjson.JSONObject;
import com.delivery.entity.Admin;
import com.delivery.entity.Standard;
import com.delivery.service.StandardService;
import com.delivery.util.PageUtil;
import com.delivery.util.TimeUtil;
import com.delivery.utilentity.FindStandard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 * Created by LEO15 on 2020/10/28.
 * @author fujianian
 */

@Controller
@RequestMapping("/sys")
public class StandardController {

    @Resource
    private StandardService standardService;

    @RequestMapping("/standardView")
    public String standardView() {
        return "base/setStandard";
    }

    @RequestMapping("/standardShow")
    public void findStandardView(int page, int rows, HttpServletResponse httpServletResponse) throws IOException {
        List<Standard> standardList = standardService.selectStandardLimit(page, rows);
        int count = standardService.standardCount();
        System.out.println(count);
        PageUtil pageUtil = new PageUtil();
        pageUtil.setTotal(count);
        pageUtil.setRows(standardList);
        httpServletResponse.getWriter().write(JSONObject.toJSONString(pageUtil));
    }

    @RequestMapping(value = "/addStandard" )
    public String addStandard(Standard standard, Model model, HttpSession session) {
        Admin admin= (Admin) session.getAttribute("admin");
        boolean flag = false;
        if (standard.getMinVolume() >= standard.getMaxVolume() || standard.getMinWeight() >= standard.getMaxWeight()) {
            String msg = "添加失败";
            model.addAttribute("msg", msg);
            return "base/setStandard";
        } else {
            standard.setStatus(1);
            standard.setOperator(admin.getAdminName());
            standard.setoStation(admin.getStations());
            standard.setoTime(TimeUtil.localtime());
            flag = standardService.addStandard(standard);
            if (flag) {
                String msg = "添加成功";
                model.addAttribute("msg", msg);
                return "base/setStandard";
            } else {
                String msg = "添加失败";
                model.addAttribute("msg", msg);
                return "base/setStandard";
            }
        }
    }

    @RequestMapping("/standardDelete")
    public String removeStandard(String ids, Model model) {
        boolean flag = false;
        System.out.println("ids = " + ids);
        //选中的id用‘,'分割
        String[] split = ids.split(",");
        for (String id : split) {
            Standard standard = new Standard();
            standard.setId(Integer.parseInt(id));
            standard.setoTime(TimeUtil.localtime());
            flag = standardService.removeStandardStatus(standard);
        }
        if (flag) {
            String msg = "删除成功";
            model.addAttribute("msg", msg);
            return "base/setStandard";
        } else {
            String msg = "删除失败";
            model.addAttribute("msg", msg);
            return "base/setStandard";
        }

    }

    @RequestMapping("/updateStandard")
    public String updateStandard(Standard standard,Model model,HttpSession session) {
        Admin admin= (Admin) session.getAttribute("admin");
        System.out.println(standard);
        boolean flag=false;
        if (standard.getMaxWeight()<=standard.getMinWeight()||standard.getMaxVolume()<=standard.getMinVolume()){
            String msg="修改失败";
            model.addAttribute("msg",msg);
            return "base/setStandard";
        }else{
            standard.setOperator(admin.getAdminName());
            standard.setoStation(admin.getStations());
            standard.setoTime(TimeUtil.localtime());
            flag=standardService.updateStandard(standard);
            if (flag){
                String msg="修改成功";
                model.addAttribute("msg",msg);
                return "base/setStandard";
            }else {
                String msg="修改失败";
                model.addAttribute("msg",msg);
                return "base/setStandard";
            }

        }
    }


    @RequestMapping("/findStandardView" )
    @ResponseBody
    public void findStandardByElements(FindStandard findStandard, HttpServletResponse response)throws IOException{
            PageUtil pageUtil =standardService.selectStandardByElements(findStandard);
             response.getWriter().write(JSONObject.toJSONString(pageUtil));
    }




}
