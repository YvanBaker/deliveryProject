package com.delivery.service;

import com.delivery.entity.QpWorkorder;
import com.delivery.util.PageUtil;

public interface WorkorderService {
    boolean workorderAdd(QpWorkorder qpWorkorder);

    PageUtil workorderAll(int page, int rows);

}
