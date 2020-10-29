package com.delivery.dao;

import com.delivery.entity.QpWorkorder;

import java.util.List;

public interface WorkorderDao {
    boolean addWorkorder(QpWorkorder qpWorkorder);

    List<QpWorkorder> getAllWorkorder(int page, int rows);

    int getAllWorkorderCount();

}
