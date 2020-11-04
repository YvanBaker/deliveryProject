package com.delivery.service.impl;

import com.delivery.dao.BusinessNoteDao;
import com.delivery.entity.BusinessNote;
import com.delivery.service.BusinessNoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        return businessNoteDao.businessNoteAdd(businessNote);
    }
}
