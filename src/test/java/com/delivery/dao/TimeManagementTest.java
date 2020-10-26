package com.delivery.dao;

import com.delivery.entity.TimeManagement;
import com.delivery.util.TimeUtil;
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
@Transactional
public class TimeManagementTest {

    private TimeManagement t;

    @Resource
    private TimeManagementDao timeManagementDao;

    @Before
    public void init(){
        t=new TimeManagement();
        t.setTimeName("时间管理1");
        t.setStation("aaa");
        t.setNormalWorkTime(TimeUtil.localtime());
        t.setNormalOffWorkTime(TimeUtil.localtime()+1);
        t.setWeekWorkTime(TimeUtil.localtime());
        t.setWeekOffWorkTime(TimeUtil.localtime());
        t.setStatus(1);
        t.setOperator("Test");
        t.setoStation("test");
        t.setoTime("ttesst");
        timeManagementDao.addTimeManagement(t);
    }

    @Test
    @Rollback
    public void testSelect(){
        List list=timeManagementDao.selectTimeManagement();
        assertFalse(list.isEmpty());
    }

    @Test
    @Rollback
    public void testUpdateAndRemove(){
        TimeManagement i=new TimeManagement();
        i.setId(1);
        i.setTimeName("aa");
        i.setNormalWorkTime("aa");
      //  i.setNormalOffWorkTime("bbc");
        i.setWeekWorkTime("vvv");
        i.setWeekOffWorkTime("ttt");
        i.setStatus(2);
        i.setOperator("xax");
        i.setoStation("pp");
        i.setoTime("gg");
        boolean f=timeManagementDao.updateAndRemoveTimeManagement(i);
        assertTrue(f);
    }

}
