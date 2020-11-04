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
        String address="上海市闵行区";
        Map<String,String > map=CutAddressUtil.addressResolution(address);
        System.out.println(map.get("prov"));

    }
}
