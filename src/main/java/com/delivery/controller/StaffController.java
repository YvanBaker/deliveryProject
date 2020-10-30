package com.delivery.controller;

import com.alibaba.fastjson.JSONObject;
import com.delivery.entity.Staff;
import com.delivery.service.StaffService;
import com.delivery.util.PageUtil;
import com.delivery.util.UuidUtil;
import com.delivery.utilentity.FindStaff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class StaffController {

    @Resource
    StaffService staffService;

    /**
     * 查看员工页面
     * @return
     */
    @RequestMapping("/staffView")
    public String staff() {
        return "base/staff";
    }

    /**
     * 查看员工 通过 工号，所属定区，收派标准，所属单位查寻
     *
     * @param findStaff 查询条件
     * @param response  响应
     * @throws IOException
     */
    @RequestMapping("/staffShow")
    public void staffShow( FindStaff findStaff,HttpServletResponse response) throws IOException {
        PageUtil pageUtil = staffService.selectStaff(findStaff);
        response.getWriter().write(JSONObject.toJSONString(pageUtil));
    }

 /*   *//**
     * TODO 查找员工 selectStaffByColumns的数据库查询语句需要写(多表联查)
     * 通过 工号，所属定区，收派标准，所属单位查寻 395887b2d9de
     * @return
     *//*


    /**
     * 添加员工
     * @param staff
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping("/staffAdd")
    public String staffAdd(Staff staff, Model model) throws IOException {
        staff.setUuid(UuidUtil.getUuid());
        if ("".equals(staff.getHaspda())||null==staff.getHaspda()){
            staff.setHaspda("N");
        }
        boolean flog = staffService.addStaff(staff);
        String msg = "添加成功";
        model.addAttribute("msg", msg);
        return "base/staff";
    }

    /**
     * 废除
     * @param ids
     * @param model
     * @return
     */
    @RequestMapping("/staffDelete")
    public String staffDelete(String ids, Model model) {
        System.out.println("ids = " + ids);//选中的id用‘,'分割
        String[] split = ids.split(",");
        for (String id:split) {
            Staff staff = new Staff();
            staff.setId(Integer.parseInt(id));
            staffService.removeStaff(staff);
        }
        String msg = "删除成功";
        model.addAttribute("msg", msg);
        return "base/staff";
    }

    /**
     * 修改
     * @param staff
     * @param model
     * @return
     */
    @RequestMapping("/staffEdit")
    public String staffEdit(Staff staff, Model model) {
        if (staff.getHaspda() == null || "".equals(staff.getHaspda())) {
            staff.setHaspda("N");
        }
        staffService.updateStaff(staff);
        String msg = "修改成功";
        model.addAttribute("msg", msg);
        return "base/staff";
    }
}
