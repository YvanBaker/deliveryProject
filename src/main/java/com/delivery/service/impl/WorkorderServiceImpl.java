package com.delivery.service.impl;

import com.delivery.dao.WorkorderDao;
import com.delivery.entity.Decidedzone;
import com.delivery.entity.Customer;
import com.delivery.entity.QpWorkorder;
import com.delivery.entity.Region;
import com.delivery.entity.StaffOrder;
import com.delivery.service.DecidedzoneService;
import com.delivery.service.RegionService;
import com.delivery.service.StaffOrderService;
import com.delivery.service.WorkorderService;
import com.delivery.util.CutAddressUtil;
import com.delivery.util.PageUtil;
import com.delivery.util.UuidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WorkorderServiceImpl implements WorkorderService {
    @Resource
    WorkorderDao workorderDao;
    RegionService regionService;
    DecidedzoneService decidedzoneService;
    StaffOrderService staffOrderService;

    /**
     * 创建 修改工作单
     * @param qpWorkorder
     * @return
     */
    @Override
    public boolean workorderAdd(QpWorkorder qpWorkorder) {
        String uuid = UuidUtil.getUuid().substring(20);

        if (qpWorkorder.getUuid() == "" || qpWorkorder.getUuid()==null) {
            qpWorkorder.setUuid(uuid);
            workorderDao.addWorkorder(qpWorkorder);
        }else {//修改工作单
            workorderDao.updataWorkorder(qpWorkorder);
        }

        return true;
    }

    /**
     * 查询所有工作单
     * @return
     * @param page
     * @param rows
     */
    @Override
    public PageUtil workorderAll(int page, int rows) {
        List<QpWorkorder> allWorkorder = workorderDao.getAllWorkorder((page - 1) * rows, rows);
        int total=workorderDao.getAllWorkorderCount();
        return new PageUtil(total,allWorkorder);
    }
  
    /**
     * 查询可关联的订单
     * @return
     */
    @Override
    public List<QpWorkorder> getNoAssociationsOrder() {
        return workorderDao.getAssociationsWorkorder();
    }

    /**
     * 查找已关联订单
     * @return
     */
    @Override
    public List<QpWorkorder> getHasAssociationsOrder() {
        return workorderDao.getHasAssociationsWorkorder();

    }

}
