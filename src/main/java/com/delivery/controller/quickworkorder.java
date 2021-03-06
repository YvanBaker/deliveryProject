package com.delivery.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.delivery.entity.QpWorkorder;
import com.delivery.service.RegionService;
import com.delivery.service.WorkorderService;
import com.delivery.util.PageUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

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
    @RequestMapping("/workorAll")
    public void workorAll(int page,int rows, HttpServletResponse response) throws IOException {
         PageUtil pageUtil= workorderService.workorderAll(page,rows);
         response.getWriter().write(JSONObject.toJSONString(pageUtil));
    }

    /**
     * 快速添加一条工作单
     * @param qpWorkorder
     * @return
     */
    @RequestMapping(value = "/workOrderAddOne",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String workOrderAddOne(QpWorkorder qpWorkorder) {
        String msg = "";
        Pattern phone = Pattern.compile("^1[3-8]+\\d{9}$");
        if (phone.matcher(qpWorkorder.getSenderphone()).matches()) {
            boolean b= workorderService.workorderAdd(qpWorkorder);
            msg= "Y";
        }else {
            msg= "N";
        }
        return JSON.toJSONString(msg);
    }


    /**
     * TODO 未测试
     * 添加工作单(快速)
     * @param qpWorkorder
     */
   /* @RequestMapping("/workorderAdd")
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
                /*站内已收，直接派送*/
//                boolean b = workorderService.workorderAdd(qpWorkorder);
//                response.getWriter().write("Y");
//            }
//        }catch (NullPointerException e){
//            response.getWriter().write("N");
//        }
//    }
}
