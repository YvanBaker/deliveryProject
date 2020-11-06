package com.delivery.service.impl;

import com.delivery.dao.BusinessNoteDao;
import com.delivery.entity.BusinessNote;
import com.delivery.service.BusinessNoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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
    @Override
    public boolean addbusinessNote(BusinessNote businessNote) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        businessNote.setBuildTime(simpleDateFormat.format(new Date()));

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
     * @param bid
     * @return
     */
    @Override
    public List<BusinessNote> AssociationsOrderOnDbl(String bid) {
        int id = Integer.parseInt(bid);
        return businessNoteDao.AssociationsOrderOnDbl(id);
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
    public List<BusinessNote> getHasAssociationsOrder() {
        return businessNoteDao.getHasAssociationsOrder();
    }
}
