package com.delivery.dao;

import com.delivery.entity.Admin;

public interface AdminDao {
    /**
     * 管理员账户验证
     * @param admin
     * @return
     */
    Admin loginVerify(Admin admin);

    /**
     * 修改管理员密码
     * @param admin
     * @return
     */
    boolean changePassword(Admin admin);
}
