package com.delivery.service.impl;

import com.delivery.entity.Standard;
import com.delivery.service.StandardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by LEO15 on 2020/10/29.
 * @author fujianian
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StandardServiceImplTest {
    @Resource
    private StandardService standardService;

    @Test
    public void selectStandardLimitTest(){
        List<Standard> standardList=standardService.selectStandardLimit(1,10);
        for (Standard s: standardList){
            System.out.println(s);
        }
        assertFalse(standardList.isEmpty());
    }
}
