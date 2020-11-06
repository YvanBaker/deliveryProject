package com.delivery.dao;

import com.delivery.entity.QpWorkorder;

import java.util.List;

public interface WorkorderDao {
    boolean addWorkorder(QpWorkorder qpWorkorder);

    List<QpWorkorder> getAllWorkorder(int page, int rows);

    int getAllWorkorderCount();

    /**
     * 分单
     * @param qpWorkorder
     * @return
     */
    boolean updateStaff(QpWorkorder qpWorkorder);

    /**
     * 分单
     * @param qpWorkorder
     * @return
     */
    QpWorkorder selectOneWorkord(QpWorkorder qpWorkorder);


    List<QpWorkorder> getAssociationsWorkorder();

    List<QpWorkorder> getHasAssociationsWorkorder();

}
