package com.delivery.controller;

import com.alibaba.fastjson.JSONObject;
import com.delivery.entity.QpWorkorder;
import com.delivery.entity.Region;
import com.delivery.service.RegionService;
import com.delivery.service.WorkorderService;
import com.delivery.util.CutAddressUtil;
import com.delivery.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys")
public class quickworkorder {
    @Resource
    WorkorderService workorderService;
    RegionService regionService;
    /**
     * 页面跳转
     * @return
     */
    @RequestMapping("/quickworkorderView")
    public String quickworkorderView() {
        return "qupai/quickworkorder";
    }

    /**
     * 分页显示工单(快速)
     * @param page
     * @param rows
     * @param response
     * @throws IOException
     */
    @RequestMapping("workorAll")
    public void workorAll(int page,int rows, HttpServletResponse response) throws IOException {
         PageUtil pageUtil= workorderService.workorderAll(page,rows);
         response.getWriter().write(JSONObject.toJSONString(pageUtil));
    }

    /**
     * TODO 未测试
     * 添加工作单(快速)
     * @param qpWorkorder
     */
    @RequestMapping("/workorderAdd")
    public void workorderAdd(QpWorkorder qpWorkorder, HttpServletResponse response) throws IOException {
        String citySend=null;
        String cityReceive=null;
        try{
            Map<String,String> map= CutAddressUtil.addressResolution(qpWorkorder.getSenderaddr());
            if ((map.get("prov").equals("北京市")||map.get("prov").equals("上海市")||
                    map.get("prov").equals("天津市")||map.get("prov").equals("重庆市"))){
                citySend = "市辖区";
            }else{
                citySend=map.get("city");
            }
            Map<String,String> map1=CutAddressUtil.addressResolution(qpWorkorder.getReceiveraddr());
            if ((map1.get("prov").equals("北京市")||map1.get("prov").equals("上海市")||
                    map1.get("prov").equals("天津市")||map1.get("prov").equals("重庆市"))){
                cityReceive="市辖区";
            }else{
                cityReceive=map1.get("city");
            }
            Region regionSend=new Region(map.get("prov"),citySend,map.get("area"));
            Region regionRece=new Region(map1.get("prov"),cityReceive,map1.get("area"));
            if (null==regionService.selectOneRegion(regionSend)||null==regionService.selectOneRegion(regionRece)){
                response.getWriter().write("N");
            }else {
                boolean b = workorderService.workorderAdd(qpWorkorder);
                response.getWriter().write("Y");
            }
        }catch (NullPointerException e){
            response.getWriter().write("N");
        }
    }




}
