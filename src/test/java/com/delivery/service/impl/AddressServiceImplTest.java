package com.delivery.service.impl;


import com.delivery.entity.CustomerAddress;
import com.delivery.service.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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
    AddressService addressService;



    @Test
    public void saveCustomerAddress() {
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setUserId(1);
        customerAddress.setAddressDetail("12345");
        CustomerAddress customerAddress1 = addressService.saveCustomerAddress(customerAddress);
        System.out.println(customerAddress1);
        assertTrue(true);
    }

    @Test
    public void testA() {
    }
}