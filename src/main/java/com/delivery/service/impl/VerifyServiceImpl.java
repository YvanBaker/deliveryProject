package com.delivery.service.impl;

import com.delivery.dao.DecidedzoneDao;
import com.delivery.entity.Decidedzone;
import com.delivery.service.VerifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wenJ
 * @Description 验证业务
 * @Classname VerifyServiceImpl
 * @Date 2020/11/3 17:08
 */
@Service
public class VerifyServiceImpl implements VerifyService {
    @Resource
    DecidedzoneDao decidedzoneDao;

    @Override
    public Decidedzone getDecidedZone(String deciname) {
        return decidedzoneDao.getDecidedZone(deciname);
    }
}
