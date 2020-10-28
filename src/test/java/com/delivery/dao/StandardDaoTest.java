package com.delivery.dao;

import com.delivery.entity.Standard;

import com.delivery.util.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author fujianian
 * @Classname StandardDaoTest
 * @Date 2020/10/21 20:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StandardDaoTest {
    @Resource
    private StandardDao standardDao;

    @Test
    public void addStandardTest(){
        Standard standard=new Standard();
        standard.setStandardName("Test");
        standard.setMinWeight(1);
        standard.setMaxWeight(5);
        standard.setMinVolume(1);
        standard.setMaxVolume(5);
        standard.setStatus(0);
        standard.setOperator("test");
        standard.setoStation("test");
        standard.setoTime(TimeUtil.localtime());
        boolean i=standardDao.addStandard(standard);
        assertTrue(i);
    }

    @Test
    public void updateStandardTest(){
        Standard standard=new Standard();
        standard.setId(1);
        standard.setStandardName("test1");
        standard.setMinWeight(1);
        standard.setMaxWeight(7);
        standard.setMinVolume(1);
        standard.setMaxVolume(6);
        standard.setOperator("test1");
        standard.setoStation("test1");
        standard.setoTime(TimeUtil.localtime());
        boolean i=standardDao.updateStandard(standard);
        assertTrue(i);
    }

    @Test
    public void removeStandardTest(){
        Standard standard = new Standard();
        standard.setId(1);
        standard.setStatus(0);
        boolean i=standardDao.removeStandard(standard);
        assertTrue(i);
    }

//    @Test
//    public void selectStandardTest(){
//        List<Standard> list=standardDao.selectStandard();
//        assertTrue(list.size()>0);
//    }

//    @Test
//    public void selectStandardByStatusTest(){
//        List<Standard> list=standardDao.selectStandardByStatus(1);
//        assertTrue(list.size()>0);
//    }

//    @Test
//    public void selectStandardByStandardName(){
//        List<Standard> list=standardDao.selectStandardByStandardName("test");
//        assertTrue(list.size()>0);
//    }
}
