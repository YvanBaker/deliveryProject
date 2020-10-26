package com.delivery.dao;

import com.delivery.entity.Staff;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by LEO15 on 2020/10/19.
 * @author fujianian
 * @Classname TestStaffDao
 * @Date 2020/10/19
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
public class TestStaffDao extends AbstractJUnit4SpringContextTests {

    @Resource
    private StaffDao staffDao;

    @Test
    public void testStaffDao(){
        Staff staff=new Staff();
        staff.setUuid("2020.10.20");
        staff.setName("test");
        staff.setTelephone("test");
        staff.setHaspda("Y");
        staff.setDeltag("Y");
        staff.setStation("test");
        staff.setStandard("test");
        System.out.println(staff.getId());
        boolean i=staffDao.addStaff(staff);
        System.out.println(i);
    }

    @Test
    public void testStaffDaoRemove(){
        Staff staff=new Staff();
        staff.setId(1);
        staff.setDeltag("N");
        boolean i =staffDao.removeStaff(staff);
        System.out.println(i);
    }

    @Test
    public void testStaffDaoUpdate(){
        Staff staff=new Staff();
        staff.setId(4);
        staff.setTelephone("13546465454");
        staff.setHaspda("");
        staff.setStation("bb");
        staff.setStandard("test");
        boolean i =staffDao.updateStaff(staff);
        System.out.println(i);
    }

    @Test
    public void testStaffDaoSelectStaffDeltagIsYes(){
        List<Staff> staff = staffDao.selectAllStaff();
        assertFalse(staff.isEmpty());
    }

    @Test
    public void testStaffDeltagIsYes(){
        List<Staff> staff=staffDao.selectStaffDeltagIsYes();
        assertFalse(staff.isEmpty());
    }

    @Test
    public void testStaffByOneElement(){
        Staff staff=new Staff();
        staff.setName("");
        staff.setUuid("2020.10.20");
        staff.setStation("test");
        List<Staff> staff1=staffDao.selectStaffByOneElement(staff);
        assertFalse(staff1.isEmpty());
    }

    @Test
    public void selectStaffByStandard(){
        List<Staff> staff=staffDao.selectStaffByStandard("test");
        assertFalse(staff.isEmpty());
    }

    @Test
    @Rollback
    public void selectStaffByDimElement(){
        Staff staff=new Staff();
        staff.setName("test");
        List<Staff> staff1=staffDao.selectStaffByDimElements(staff);
        assertFalse(staff1.isEmpty());
    }

    @Test
    @Rollback
    public void selectStaffStandardLineInstead(){
        List list=staffDao.selectStaffStandardLineInstead();
        assertFalse(list.isEmpty());

    }


}
