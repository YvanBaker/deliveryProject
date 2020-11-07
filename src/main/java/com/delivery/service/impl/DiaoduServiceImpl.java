package com.delivery.service.impl;

import com.delivery.dao.BusinessNoteDao;
import com.delivery.dao.StaffDao;
import com.delivery.dao.StaffOrderDao;
import com.delivery.entity.BusinessNote;
import com.delivery.entity.Staff;
import com.delivery.service.DiaoduService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wenJ
 * @Description 调度服务
 * @Classname DiaoduServiceImpl
 * @Date 2020/11/7 9:45
 */
@Service
public class DiaoduServiceImpl implements DiaoduService {
    @Resource
    StaffDao staffDao;

    @Resource
    BusinessNoteDao businessNoteDao;

    /**
     * 选取取派员
     *
     * @param q
     * @return
     */
    @Override
    public List<Staff> associationsStaffAjax(String q) {
        return staffDao.associationsStaff(q);
    }

    /**
     * 订单关联取派员
     *
     * @return
     */
    @Override
    public List<BusinessNote> findBusinessNote() {
        return businessNoteDao.getBusinessNotes();
    }

    /**
     * 提交关联
     * @param orderId
     * @param staffId
     * @return
     */
    @Override
    public boolean diaoduOrderAssociationsStaff(String orderId, String staffId) {
        int oid = Integer.parseInt(orderId);
        return businessNoteDao.diaoduOrderAssociationsStaff(oid,staffId);
    }
}
