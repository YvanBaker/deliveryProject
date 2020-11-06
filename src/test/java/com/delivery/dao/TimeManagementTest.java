package com.delivery.dao;

import com.delivery.entity.TimeManagement;
import com.delivery.util.TimeUtil;
import com.delivery.utilentity.FindTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by LEO15 on 2020/10/26.
 * @author fujianian
 * @className timeManagementTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
//@Transactional
public class TimeManagementTest {

    private TimeManagement t;

    @Resource
    private TimeManagementDao timeManagementDao;

//    @Before
//    public void init(){
//        t=new TimeManagement();
//        t.setTimeName("时间管理1");
//        t.setStation("aaa");
//        t.setNormalWorkTime(TimeUtil.localtime());
//        t.setNormalOffWorkTime(TimeUtil.localtime());
//        t.setWeekWorkTime(TimeUtil.localtime());
//        t.setWeekOffWorkTime(TimeUtil.localtime());
//        t.setStatus(1);
//        t.setOperator("Test");
//        t.setoStation("test");
//        t.setoTime("ttesst");
//        timeManagementDao.addTimeManagement(t);
//    }

    @Test
    public void testAdd(){
        t=new TimeManagement();
        t.setTimeName("时间管理1");
        t.setStation("aaa");
        t.setNormalWorkTime(TimeUtil.localtime());
        t.setNormalOffWorkTime(TimeUtil.localtime());
        t.setWeekWorkTime(TimeUtil.localtime());
        t.setWeekOffWorkTime(TimeUtil.localtime());
        t.setStatus(1);
        t.setOperator("Test");
        t.setoStation("test");
        t.setoTime("ttesst");
        boolean f=timeManagementDao.addTimeManagement(t);
        assertTrue(f);
    }

    @Test
    @Rollback
    public void testSelect(){
        FindTime findTime= new FindTime();
        findTime.setTimeName("时间管理1");
        List list=timeManagementDao.selectTimeManagement(findTime);
        assertFalse(list.isEmpty());
    }

    @Test
    @Rollback
    public void testUpdate(){
        TimeManagement i=new TimeManagement();
        i.setId(10);
        i.setTimeName("aab");
        i.setNormalWorkTime("aab");
        i.setNormalOffWorkTime("bbcb");
        i.setWeekWorkTime("vvva");
        i.setWeekOffWorkTime("tttb");
        i.setOperator("xaxb");
        i.setoStation("ppb");
        i.setoTime("ggb");
        boolean f=timeManagementDao.updateTimeManagement(i);
        assertTrue(f);
    }
    @Test
    @Rollback
    public void testRemove(){
        TimeManagement i=new TimeManagement();
        i.setId(10);
        i.setOperator("xax");
        i.setoStation("pp");
        i.setoTime("gg");
        boolean f=timeManagementDao.updateTimeManagement(i);
        assertTrue(f);
    }

}
