package com.delivery.dao;

import com.alibaba.fastjson.JSON;
import com.delivery.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Yvan
 * @Description
 * @Classname UserDaoTest
 * @Date 2020/10/16 18:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserDaoTest {

    @Resource
    private UserDao userDao;

    @Test
    public void insertUserTest() {
        User user = new User();
        user.setUserName("test");
        user.setUserPassword("test");
        user.setUserSalary(10.0);
        user.setUserDate(new Date());
        user.setUserGender("test");
        user.setUserCompany("test");
        user.setUserPhone("test");
        user.setUserRemark("test");
        user.setEmail("test");
        System.out.println(user.getId());
        int i = userDao.insertUser(user);
        System.out.println(user.getId());
        assertTrue(i != 0);
    }

    @Test
    public void updateUserTest() {
        User user = new User();
        user.setId(1);
        user.setUserPassword("123");
        int i = userDao.updateUser(user);
        assertTrue(i != 0);
    }

    @Test
    public void selectUserAllTest() {
        PageHelper.startPage(1, 2);
        List<User> users = userDao.selectUserAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        System.out.println(JSON.toJSONString(pageInfo));
    }

    @Test
    public void selectUserByTest() {
        User user = new User();
        user.setEmail("test");
//        user.setUserName("test");
        List<User> userList = userDao.selectUserBy(user);

    }
}