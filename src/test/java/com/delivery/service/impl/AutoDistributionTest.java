package com.delivery.service.impl;

import com.delivery.dao.CustomerWorkOrderDao;
import com.delivery.dao.StaffOrderDao;
import com.delivery.entity.BusinessNote;
import com.delivery.entity.CustomerWorkOrder;
import com.delivery.entity.QpWorkorder;
import com.delivery.entity.StaffOrder;
import com.delivery.service.AutoDistributionService;
import com.delivery.util.UuidUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by LEO15 on 2020/11/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AutoDistributionTest {
    @Resource
    AutoDistributionService autoDistributionService;
    @Resource
    CustomerWorkOrderDao customerWorkOrderDao;
    @Resource
    StaffOrderDao staffOrderDao;

    @Test
    public void TestAutoDistributionForReceiverQpWork(){
        QpWorkorder qpWorkorder=new QpWorkorder();
        qpWorkorder.setUuid(UuidUtil.getUuid());
        qpWorkorder.setReceiveraddr("北京市北京市东城区");
        int a=autoDistributionService.autoDistributionForReceiverQpWork(qpWorkorder);
        System.out.println(a);
    }

    @Test
    public void TestAutoDistributionForBusinessNote(){
        BusinessNote businessNote=new BusinessNote();
        businessNote.setUuid(UuidUtil.getUuid());
        businessNote.setArriveCity("北京市北京市东城区");
        int a=autoDistributionService.autoDistributionForReceiverBusinessNote(businessNote);
        System.out.println(a);
    }

    @Test
    public void TestAutoDistributionForGetGoodsAboutStaffOrder(){
        CustomerWorkOrder customerWorkOrder=customerWorkOrderDao.selectOrderById(1);
        int a=autoDistributionService.autoDistributionForGetGoodsAboutStaffOrder(customerWorkOrder);
        System.out.println(a);
    }

    @Test
    public void TestAutoDistributionForSendGoodsAboutChangeStaffOrder(){
        StaffOrder staffOrder=staffOrderDao.selectOneOrderByOrderIdForTest(23);
        System.out.println("查询修改的订单"+staffOrder);
        int a=autoDistributionService.autoDistributionForSendGoodsAboutChangeStaffOrder(staffOrder);
    }
    @Test
    public void testrandom(){
        int choiceStaff=(int) (Math.random() * (2));
        System.out.println(choiceStaff);
    }


}
