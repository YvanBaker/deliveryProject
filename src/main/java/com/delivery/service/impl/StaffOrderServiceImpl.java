package com.delivery.service.impl;


import com.delivery.dao.BusinessNoteDao;
import com.delivery.dao.DecidedzoneDao;
import com.delivery.dao.StaffOrderDao;
import com.delivery.entity.Decidedzone;
import com.delivery.entity.StaffOrder;
import com.delivery.service.StaffOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wenJ
 * @Description 分区关联订单
 * @Classname StaffOrderServiceImpl
 * @Date 2020/11/5 10:42
 */
@Service
public class StaffOrderServiceImpl implements StaffOrderService {
    @Resource
    StaffOrderDao staffOrderDao;
    @Resource
    DecidedzoneDao decidedzoneDao;

    @Resource
    BusinessNoteDao businessNoteDao;
    
     @Override
    public boolean addStaffOrder(StaffOrder staffOrder) {
        if (staffOrderDao.insertOrder(staffOrder)>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Order添加到分区关联
     *
     * @param did      定区id
     * @param orderIds 多个订单uuid
     * @return
     */
    @Override
    public boolean addAssignOrders(String did, String orderIds) {
        int id = Integer.parseInt(did);
        Decidedzone decidedzone = decidedzoneDao.getDecidedZoneById(id);
        businessNoteDao.setBusinessNoteStaffIsNull(decidedzone.getStaff().getId());//添加前先清空
        staffOrderDao.deleThisAssignOrders(decidedzone.getRegion().getAreasId(), decidedzone.getStaff().getId());//添加前先清空
        if (orderIds != null && orderIds != "") {
            String[] ids = orderIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                businessNoteDao.setStaffById(ids[i], decidedzone.getStaff().getId());
                staffOrderDao.addAssignOrders(decidedzone.getRegion().getAreasId(), decidedzone.getStaff().getId(), ids[i], 0);
            }
        }
        return true;
    }
}
