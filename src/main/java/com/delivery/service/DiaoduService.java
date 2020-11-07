package com.delivery.service;

import com.delivery.entity.BusinessNote;
import com.delivery.entity.Staff;

import java.util.List;

/**
 * @author wenJ
 * @Description
 * @Classname DiaoduService
 * @Date 2020/11/7 9:44
 */
public interface DiaoduService {

    List<Staff> associationsStaffAjax(String q);

    List<BusinessNote> findBusinessNote();

    boolean diaoduOrderAssociationsStaff(String orderId, String staffId);
}
