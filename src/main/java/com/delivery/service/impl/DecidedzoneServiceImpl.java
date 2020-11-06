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


    @Override
    public List<Decidedzone> selectOneDecidedzone(String regionId) {
        return decidedzoneDao.selectOneDecidedzone(regionId);
    }
    /**
     * 模糊查询定区
     *
     * @param page
     * @param rows
     * @param id
     * @param station
     * @return
     */
    @Override
    public PageUtil getDecidedZoneDim(int page, int rows, int id, String station) {
        page = (page - 1) * rows;
        List<Decidedzone> decidedzones = decidedzoneDao.getDecidedZonelimitDim(page, rows, id, station);
        int total=decidedzoneDao.getDecidedZoneConutDim(id,station);
        return new PageUtil(total,decidedzones);
    }

    /**
     * 删除
     * @param s
     * @return
     */
    @Override
    public boolean delectDecidedzone(String s) {
        int id = Integer.valueOf(s);
        return decidedzoneDao.delectDecidedzone(id);
    }

    /**
     * 修改
     *
     * @param areaId
     * @param staffId
     * @param decidedName
     * @param selectId
     * @return
     */
    @Override
    public boolean changDecidedzone(String areaId, String staffId, String decidedName, String selectId) {
        int id = Integer.parseInt(selectId);
        return decidedzoneDao.updateDecidedZone(areaId, staffId, decidedName, id);
    }

    /**
     * 去重
     * @param decidedName
     * @return
     */
    @Override
    public Decidedzone getDecidedZone(String decidedName) {
        return decidedzoneDao.getDecidedZone(decidedName);
    }


}
