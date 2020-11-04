package com.delivery.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.delivery.entity.Region;
import com.delivery.entity.Subarea;
import com.delivery.service.RegionService;
import com.delivery.util.PageUtil;
import com.delivery.util.UuidUtil;
import com.delivery.utilentity.ShowSubarea;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class RegionController {
    @Resource
    private RegionService regionService;


    /**
     * 跳转分区页面
     *
     * @return
     */
    @RequestMapping("/regionView")
    public String regionView() {
        return "base/region";
    }

    /**
     * 分页展示分区
     *
     * @param showSubarea
     * @return
     */
    @RequestMapping(value = "/subareaPageQuery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String subareaPageQuery(ShowSubarea showSubarea) {
        System.out.println("showSubarea = " + showSubarea);
        PageUtil pageSubarea = regionService.selectSubarealimit(showSubarea);
        System.out.println("pageSubarea = " + pageSubarea);
        for (Object row : pageSubarea.getRows()) {
            System.out.println("row = " + row);
        }
        return JSON.toJSONString(pageSubarea);
    }

    /**
     * 批量导入
     */
    @RequestMapping("/importFile")
    @ResponseBody
    public String importFile(@RequestParam("myFile") CommonsMultipartFile myFile) {
        System.out.println(myFile);
        return "1";
    }

    /**
     * 区域显示
     *
     * @param page
     * @param rows
     * @param model
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/RegionQuery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public void RegionQuery(int page, int rows, Model model, HttpServletResponse response) throws IOException {
        List<Region> regionList = regionService.selectRegionLimit(page, rows);
        int count = regionService.regionCount();
        PageUtil pageUtil = new PageUtil();
        pageUtil.setTotal(count);
        pageUtil.setRows(regionList);
        response.getWriter().write(JSONObject.toJSONString(pageUtil));
    }



    /**
     * regionAjax 搜索框查找
     */
    @RequestMapping(value = "/regionListAjax", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String regionListAjax(String q) {
        System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq = " + q);
        List<Region> regions = regionService.findAllRegionLikP(q);
        return JSON.toJSONString(regions);
    }

    /**
     * From提交添加分区信息
     *
     * @return
     */
    @RequestMapping(value = "/subareaFromAdd")
    public String subareaFromAdd(Model model, Subarea subarea) {
        subarea.setDecidedzoneId(UuidUtil.getUuid().substring(20));
        boolean folg = regionService.insertSubarea(subarea);
        model.addAttribute("msg", "添加成功");
        return "base/divideArea";
    }
}
