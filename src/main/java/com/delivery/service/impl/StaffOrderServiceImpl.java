package com.delivery.service.impl;

import com.delivery.dao.BusinessNoteDao;
import com.delivery.dao.DecidedzoneDao;
import com.delivery.dao.StaffOrderDao;
import com.delivery.entity.Decidedzone;
import com.delivery.service.StaffOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    /**
     * Order添加到分区关联
     * @param did
     * @param orderIds
     * @return
     */
    @Override
    public boolean addAssignOrders(String did, String orderIds) {
      /*  int id = Integer.parseInt(did);
        //为订单分取派员
        Decidedzone decidedzone= businessNoteDao.getDecidedZoneById(id);*/
        int id = Integer.parseInt(did);
        Decidedzone decidedzone=decidedzoneDao.getDecidedZoneById(id);
        String[] ids = orderIds.split(",");
        for (int i = 0; i < ids.length; i++) {
            staffOrderDao.addAssignOrders(did,decidedzone.getStaff().getId(),ids[i]);
        }
        return true;
    }
}
