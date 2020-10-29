package com.delivery.service.impl;

import com.delivery.dao.AdminDao;
import com.delivery.entity.Admin;
import com.delivery.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminDao adminDao;

    /**
     * 管理员登录
     * @param admin 登录账户
     * @return
     */
    @Override
    public Admin login(Admin admin) {
        return adminDao.loginVerify(admin);
    }

    /**
     * 修改密码
     * @param admin
     * @return
     */
    @Override
    public boolean editPassword(Admin admin) {
        return adminDao.changePassword(admin);
    }
}
