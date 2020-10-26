package com.delivery.dao;

import com.delivery.entity.Customer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Yvan
 * @Description
 * @Classname CustomerDaoTest
 * @Date 2020/10/23 17:24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Transactional
public class CustomerDaoTest {

    @Resource
    protected CustomerDao customerDao;

    private static Customer data1;
    private static Customer data2;

    @BeforeClass
    public static void initData() {
        data1 = new Customer();
        data1.setPhone("11034567890");
        data1.setPassword("123456");
        data1.setName("test1");
        data1.setCustomerEmail("11@xx.xx");
        data1.setSex(0);
        data2 = new Customer();
        data2.setPhone("11034567890");
        data2.setPassword("123456");
        data2.setName("test2");
        data2.setCustomerEmail("11@xx.xx");
        data2.setSex(0);
    }

    @Before
    public void before(){
        customerDao.insertCustomer(data1);
    }

    @Test
    @Rollback
    public void insertCustomer() {
        int i = customerDao.insertCustomer(data2);
        assertTrue(i > 0);
    }

    @Test
    @Rollback
    public void updateCustomer() {
        int c = customerDao.insertCustomer(data2);
        data2.setPhone("11111111");
        data2.setPassword("111111");
        data2.setName("test11");
        data2.setCustomerEmail("111@sss.ccc");
        data2.setSex(1);
        int i = customerDao.updateCustomer(data2);
        assertTrue(i > 1);
    }

    @Test
    @Rollback
    public void selectCustomerById() {
        System.out.println(data1.getId());
        Customer customer = customerDao.selectCustomerById(data1.getId());
        assertNotNull(customer);
    }

    @Test
    public void selectCustomerByName() {
        Customer customer = customerDao.selectCustomerByName(data1.getName());
        assertNotNull(customer);
    }

    @Test
    public void selectCustomerByEmail() {
        Customer customer = customerDao.selectCustomerByEmail(data1.getCustomerEmail());
        assertNotNull(customer);
    }

    @Test
    @Rollback
    public void selectCustomerByPhone() {
        Customer customer = customerDao.selectCustomerByPhone(data1.getPhone());
        assertNotNull(customer);
    }

    @Test
    @Rollback
    public void selectCustomerByNameAndPassword() {
        Customer customer = customerDao.selectCustomerByNameAndPassword(data1.getName(), data1.getPassword());
        assertNotNull(customer);
    }

    @Test
    @Rollback
    public void selectCustomerBySex() {
        List<Customer> res = customerDao.selectCustomerBySex(0);
        assertFalse(res.isEmpty());
    }
}