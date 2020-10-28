package com.delivery.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.delivery.entity.Region;
import com.delivery.service.RegionService;
import com.delivery.util.PageUtil;
import com.github.pagehelper.PageInfo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys")
public class RegionController {
    @Resource
    private RegionService regionService;

    @RequestMapping("/regionView")
    public String regionView() {
        return "base/region";
    }

    /**
     * 批量导入
     */
    @RequestMapping("/importFile")
    @ResponseBody
    public String importFile(@RequestParam("myFile") CommonsMultipartFile myFile) {
        System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        System.out.println(myFile);
        return "1";
    }

    @RequestMapping(value = "/RegionQuery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public void RegionQuery(int page,int rows,Model model, HttpServletResponse response) throws IOException {
//        PageInfo<Region> regionList=regionService.findAllRegion();
//        Map<String,Object> map = new HashMap<>();
//        map.put("rows",regionList.getList());
//        map.put("total",regionList.getTotal());
//        return JSON.toJSONString(map);

        List<Region> regionList=regionService.selectRegionLimit(page,rows);
        int count =regionService.regionCount();
        PageUtil pageUtil=new PageUtil();
        pageUtil.setTotal(count);
        pageUtil.setRows(regionList);
        response.getWriter().write(JSONObject.toJSONString(pageUtil));
    }

}
