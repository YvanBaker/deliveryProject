package com.delivery.service.impl;

import com.delivery.dao.BusinessNoteDao;
import com.delivery.dao.DecidedzoneDao;
import com.delivery.dao.StaffOrderDao;
import com.delivery.entity.BusinessNote;
import com.delivery.entity.Decidedzone;
import com.delivery.entity.StaffOrder;
import com.delivery.service.BusinessNoteService;
import com.delivery.util.UuidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wenJ
 * @Description 业务单服务
 * @Classname BusinessNoteService
 * @Date 2020/11/1 23:23
 */
@Service
public class BusinessNoteServiceImpl implements BusinessNoteService {
    @Resource
    BusinessNoteDao businessNoteDao;

    @Resource
    DecidedzoneDao decidedzoneDao;

    @Resource
    StaffOrderDao staffOrderDao;
    @Override
    public boolean addbusinessNote(BusinessNote businessNote) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        businessNote.setBuildTime(simpleDateFormat.format(new Date()));
        businessNote.setUuid(UuidUtil.getUuid().substring(20));
        return businessNoteDao.businessNoteAdd(businessNote);
    }

    /**
     * 显示所有业务单
     * @return
     */
    @Override
    public List<BusinessNote> getBusinessNote() {
        return businessNoteDao.getBusinessNotes();
    }

    /**
     * 双击查看关联订单
     * @param did
     * @return
     */
    @Override
    public List<BusinessNote> AssociationsOrderOnDbl(String did) {
        System.out.println("did = " + did);
        int id = Integer.parseInt(did);
        //查定区id
        Decidedzone decidedZoneById = decidedzoneDao.getDecidedZoneById(id);//查出定区
        System.out.println("decidedZoneById = " + decidedZoneById);
        List<StaffOrder> staffOrders = staffOrderDao.findAssociationsOrder(decidedZoneById.getStaff().getId(), decidedZoneById.getRegion().getAreasId());
        for (StaffOrder staffOrder : staffOrders) {
            System.out.println("staffOrder = " + staffOrder);
        }
        List<BusinessNote> businessNotes = new ArrayList<>();
        for (StaffOrder staffOrder : staffOrders) {
            BusinessNote businessNote= businessNoteDao.getBusinessNotesByUuid(staffOrder.getOrderId());
            System.out.println("businessNote = " + businessNote);
            businessNotes.add(businessNote);
        }
        return businessNotes;
    }

    /**
     * 未被关联的订单
     * @return
     */
    @Override
    public List<BusinessNote> getNoAssociationsOrder() {

        return businessNoteDao.getNoAssociationsOrder();
    }

    /**
     * 已被关联的订单
     * @return
     */
    @Override
    public List<BusinessNote> getHasAssociationsOrder(String did) {
        //did定区id
        int id = Integer.parseInt(did);
        Decidedzone decidedZoneById = decidedzoneDao.getDecidedZoneById(id);
        List<StaffOrder> staffOrders = staffOrderDao.findAssociationsOrder(decidedZoneById.getStaff().getId(), decidedZoneById.getRegion().getAreasId());
        List<BusinessNote> businessNotes = new ArrayList<>();
        for (StaffOrder staffOrder : staffOrders) {
            BusinessNote businessNote= businessNoteDao.getBusinessNotesByUuid(staffOrder.getOrderId());
            businessNotes.add(businessNote);
        }
        return businessNotes;
    }
}
