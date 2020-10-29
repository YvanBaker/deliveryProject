package com.delivery.service.impl;

import com.delivery.entity.Region;
import com.delivery.entity.Staff;
import com.delivery.service.StaffService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StaffServicImplTest {
    @Resource
    StaffService staffService;
    @Test
    public void staffTest() {//测试
        Staff staff = new Staff();
        System.out.println(" = -------------------------------------------------------");
        boolean b = staffService.addStaff(staff);
        System.out.println("b = " + b);
        assertTrue(b);
    }
}
