package com.delivery.controller;

import com.alibaba.fastjson.JSONObject;
import com.delivery.entity.QpWorkorder;
import com.delivery.service.WorkorderService;
import com.delivery.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class quickworkorder {
    @Resource
    WorkorderService workorderService;
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
     * 添加工作单(快速)
     *
     * @param qpWorkorder
     */
    @RequestMapping("/workorderAdd")
    public void workorderAdd(QpWorkorder qpWorkorder, HttpServletResponse response) throws IOException {
        boolean b = workorderService.workorderAdd(qpWorkorder);
        response.getWriter().write("Y");
    }


}
