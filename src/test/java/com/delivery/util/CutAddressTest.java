package com.delivery.util;

import com.delivery.entity.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by LEO15 on 2020/11/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CutAddressTest {
    @Test
    public void CutAddress(){
        String address="北京市北京市东城区新装镇**小区";
        Map<String,String > map=CutAddressUtil.addressResolution(address);
        System.out.println(map.get("prov")+" *"+map.get("city")+" *"+map.get("areas")+" *"+map.get("town")+"*"+ map.get("village"));
    }
}