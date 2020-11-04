package com.delivery.service.impl;

import com.delivery.entity.Region;
import com.delivery.service.AddressService;
import com.delivery.service.RegionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Yvan
 * @Description
 * @Classname AddressServiceImplTest
 * @Date 2020/10/22 9:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AddressServiceImplTest {

    @Resource
    RegionService regionService;

    @Test
    public void saveCustomerAddress() {
        String q = "北";
        List<Region> regions = regionService.findAllRegionLikP(q);
        for (Region region : regions) {
         System.out.println("region = " + region.getName());
        }
        assertFalse(regions.isEmpty());
    }

    @Test
    public void testA() {

    }
}