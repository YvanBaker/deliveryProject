package com.delivery.controller;

import com.alibaba.fastjson.JSONObject;
import com.delivery.entity.Region;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/sys")
public class RegionController {
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

    @RequestMapping("/RegionQuery")
    @ResponseBody
    public void RegionQuery(Model model, HttpServletResponse response) throws IOException {
        model.addAttribute("province", "北京");
        Region region = new Region();
        region.setProvince("北京");
        region.setCity("北京市");
        response.getWriter().write(JSONObject.toJSONString(region)) ;
    }

    @RequestMapping("/addRegion")
    public String addRegion(String province) {
        System.out.println(province);
        return "";
    }
}
