package com.delivery.service.impl;

import com.delivery.dao.*;
import com.delivery.entity.*;
import com.delivery.service.DecidedzoneService;
import com.delivery.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wenJ
 * @Description 找要关联的顾客
 * @Classname DecidedzoneServiceImpl
 * @Date 2020/10/31 15:41
 */
@Service
public class DecidedzoneServiceImpl implements DecidedzoneService {
    @Resource
    CustomerDao customerDao;
    @Resource
    RegionDao regionDao;
    @Resource
    SubareaDao subareaDao;
    @Resource
    DecidedzoneDao decidedzoneDao;

    @Override
    public List<Customer> getNoAssociationsCustomer() {
        return customerDao.selectNoAssociationsCustomer();
    }

    /**
     * 查询所有定区
     *
     * @return
     */
    @Override
    public List<Subarea> findAllSubarea() {
        return subareaDao.getSubareaAll();
    }

    /**
     * 添加定区
     *
     * @param decidedzone
     * @return
     */
    @Override
    public boolean addDecidedzone(Decidedzone decidedzone) {
        return decidedzoneDao.addDecidedzone(decidedzone);
    }

    /**
     * 显示定区
     *
     * @return
     */
    @Override
    public PageUtil getDecidedZoneAll(int page, int rows) {
        page = (page - 1) * rows;
        List<Decidedzone> decidedzones = decidedzoneDao.getDecidedZonelimit(page, rows);
        int total = decidedzoneDao.DecidedzoneCount();
        return new PageUtil(total, decidedzones);
    }

    /**
     * 关联区
     *
     * @return
     */
    @Override
    public List<Region> associationsRegionAjax(String q) {
        return regionDao.regionLikP("%" + q + "%");
    }

    /**
     * 添加定区
     * @param areaId
     * @param staffId
     * @param decidedName
     * @return
     */

    @Override
    public boolean addDecidedzone(String areaId, String staffId, String decidedName) {
        return decidedzoneDao.addDecidedzonePuls(areaId,staffId,decidedName);
    }


}
