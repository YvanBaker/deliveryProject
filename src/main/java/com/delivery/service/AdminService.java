package com.delivery.service;

import com.delivery.entity.Admin;

public interface AdminService {

    /**
     * 管理员登录
     * @param admin 登录账户
     * @return
     */
    Admin login(Admin admin);

    boolean editPassword(Admin admin);
}
