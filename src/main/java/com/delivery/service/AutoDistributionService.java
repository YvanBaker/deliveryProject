package com.delivery.service;

import com.delivery.entity.BusinessNote;
import com.delivery.entity.CustomerWorkOrder;
import com.delivery.entity.QpWorkorder;
import com.delivery.entity.StaffOrder;

/**
 * Created by LEO15 on 2020/11/4.
 * 自动分单
 * 返回int类型
 * 返回 -1 区找不到
 * 返回 -2 找不到定区中的取派员
 * 返回 1 正常
 * 返回 0 未知错误
 */

public interface AutoDistributionService {

    /**
     * 自动分单——站内寄送
     * 即与staff_Order关联
     * @param qpWorkorder
     * @return
     */
    int autoDistributionForReceiverQpWork(QpWorkorder qpWorkorder);

    /**
     * 自动分单——站内寄送
     * 即与staff_Order关联
     * @param businessNote
     * @return
     */
    int autoDistributionForReceiverBusinessNote(BusinessNote businessNote);

    /**
     * 自动分单——取件（网页预约使用，极简版）
     * @param customerWorkOrder
     * @return
     */
    int autoDistributionForGetGoodsAboutStaffOrder(CustomerWorkOrder customerWorkOrder);

    /**
     * 自动分单——送件（取件后转单使用，极简版）
     * @param staffOrder
     * @return
     */
    int autoDistributionForSendGoodsAboutChangeStaffOrder(StaffOrder staffOrder);


}
