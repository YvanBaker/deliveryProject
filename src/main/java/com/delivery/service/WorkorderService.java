package com.delivery.service;

import com.delivery.entity.Customer;
import com.delivery.entity.QpWorkorder;
import com.delivery.util.PageUtil;

import java.util.List;

public interface WorkorderService {
    boolean workorderAdd(QpWorkorder qpWorkorder);

    PageUtil workorderAll(int page, int rows);

    List<QpWorkorder> getNoAssociationsOrder();

    List<QpWorkorder> getHasAssociationsOrder();

}
