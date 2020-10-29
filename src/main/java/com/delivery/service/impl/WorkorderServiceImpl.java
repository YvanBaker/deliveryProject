package com.delivery.service.impl;

import com.delivery.dao.WorkorderDao;
import com.delivery.entity.QpWorkorder;
import com.delivery.service.WorkorderService;
import com.delivery.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WorkorderServiceImpl implements WorkorderService {
    @Resource
    WorkorderDao workorderDao;

    @Override
    public boolean workorderAdd(QpWorkorder qpWorkorder) {
        return workorderDao.addWorkorder(qpWorkorder);
    }

    /**
     * 查询所有工作单
     * @return
     * @param page
     * @param rows
     */
    @Override
    public PageUtil workorderAll(int page, int rows) {
        List<QpWorkorder> allWorkorder = workorderDao.getAllWorkorder((page-1)*rows,rows);
        int total=workorderDao.getAllWorkorderCount();
        return new PageUtil(total,allWorkorder);
    }
}
