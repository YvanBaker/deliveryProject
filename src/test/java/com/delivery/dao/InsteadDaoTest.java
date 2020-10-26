package com.delivery.dao;

import com.delivery.entity.Instead;
import com.delivery.util.TimeUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by LEO15 on 2020/10/26.
 * @author fujianina
 * @classname InsteadDaoTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Transactional
public class InsteadDaoTest {
    private Instead data1;
    @Resource
    private InsteadDao insteadDao;

    @Before
    public void init(){
        data1=new Instead();
        data1.setReplacedStaffUuid("1");
        data1.setReplacedStaffName("test");
        data1.setInsteadStaffUuid("asdfafad");
        data1.setInsteadStaffName("test");
        data1.setStartTime(TimeUtil.localtime());
        data1.setEndTime(TimeUtil.localtime()+1);
        data1.setStatus(1);
        data1.setOperator("test");
        data1.setoStation("test");
        data1.setoTime(TimeUtil.localtime());
        insteadDao.addInstead(data1);
    }

    @Test
    @Rollback
    public void testSelectInstead(){
        List list=insteadDao.selectInstead(1,"1");
        assertFalse(list.isEmpty());
    }

    @Test
    @Rollback
    public void testRemoveInstesd(){
        Instead instead=new Instead();
        instead.setStatus(1);
        instead.setOperator("aadfa");
        instead.setoStation("tset");
        instead.setoTime(TimeUtil.localtime());
        boolean flag=insteadDao.updateAndRemoveInstead(instead);
        assertTrue(flag);
    }

    @Test
    @Rollback
    public void testUpdateInstesd(){
        Instead instead=new Instead();
        instead.setInsteadStaffUuid("3");
        instead.setInsteadStaffName("test3");
        instead.setOperator("aadfa");
        instead.setoStation("tset");
        instead.setoTime(TimeUtil.localtime());
        boolean f =insteadDao.updateAndRemoveInstead(instead);
        assertTrue(f);
    }

}
