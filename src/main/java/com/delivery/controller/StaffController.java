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
     * 查看员工
     *
     * @param page      当前页
     * @param rows      记录条数
     * @param findStaff 查询条件
     * @param response  响应
     * @throws IOException
     */
    @RequestMapping("/staffShow")
    public void staffShow(int page, int rows, FindStaff findStaff,Model model, HttpServletResponse response) throws IOException {
        System.out.println("findStaffssssssssssssssssssssssssssssssssssssss = " + findStaff);
        List<Staff> staff = staffService.selectStaffByPage(page, rows);
        int total= staffService.staffDelIsYTotal();
        System.out.println("total =sssssssssssssssssssssssssssaaaaaaaaaaaaaaaaaaaaa " + total);
        PageUtil pageUtil = new PageUtil();
        pageUtil.setTotal(total);
        pageUtil.setRows(staff);
        //List<Staff> staff = staffService.selectStaffDeltagIsYes();total
        response.getWriter().write(JSONObject.toJSONString(pageUtil));
    }

    /**
     * TODO 查找员工 selectStaffByColumns的数据库查询语句需要写(多表联查)
     * 通过 工号，所属定区，收派标准，所属单位查寻 395887b2d9de
     * @return
     */
    @RequestMapping("/findStaffView")
    public void findStaffView(FindStaff findStaff,HttpServletResponse response) throws IOException {
        System.out.println("findStaff = " + findStaff);
        Staff staff= staffService.selectStaffByColumns(findStaff);
        System.out.println("staff = " + staff);
        response.getWriter().write(JSONObject.toJSONString(staff));
//        return "base/staff";
    }

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