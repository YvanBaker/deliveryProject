package com.delivery.service.impl;

import com.delivery.dao.StaffOrderDao;
import com.delivery.entity.StaffOrder;
import com.delivery.service.StaffOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by LEO15 on 2020/11/4.
 */
@Service
public class StaffOrderServiceImpl implements StaffOrderService{

    @Resource
    private StaffOrderDao staffOrderDao;
    @Override
    public boolean addStaffOrder(StaffOrder staffOrder) {
        if (staffOrderDao.insertOrder(staffOrder)>0){
            return true;
        }else{
            return false;
        }

    }
}
