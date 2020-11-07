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

    @Resource
    StaffOrderDao staffOrderDao;
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
     *
     * @param areaId
     * @param staffId
     * @param decidedName
     * @return
     */

    @Override
    public boolean addDecidedzone(String decidedName, String staffId, String areaId) {
        return decidedzoneDao.addDecidedzonePuls(areaId, staffId, decidedName);
    }


    @Override
    public List<Decidedzone> selectOneDecidedzone(String regionId) {
        return decidedzoneDao.selectOneDecidedzone(regionId);
    }
    /**
     * 改进的分页查询并模糊查询定区
     *
     * @param page    第几页
     * @param rows    行数
     * @param name    定区名字
     * @param station 所属公司
     * @return
     */
    @Override
    public PageUtil getDecidedZoneShow(int page, int rows, String name, String station) {
        List<Decidedzone> decidedzones = new ArrayList<>();
        int total = 0;
        page = (page - 1) * rows;
        if (station != "" && station != null) {//如果所属公司不为空
            station = "%" + station + "%";//模糊查询
        }
        if (name != "" && name != null) {
            name = "%" + name + "%";
        }
        System.out.println("name = " + name);
        System.out.println("station = " + station);
        decidedzones = decidedzoneDao.getDecidedZonelimitDim(page, rows, name, station);
        total = decidedzoneDao.getDecidedZoneConutDim(name, station);
        /*if ((station == null && name == null)||(station == "" && name == "")) {//如果空参全查
            decidedzones = decidedzoneDao.getDecidedZonelimit(page, rows);
            total = decidedzoneDao.DecidedzoneCount();
        }*/
        return new PageUtil(total, decidedzones);
    }



    /**
     * 是否有关联 为true 表示没有关联
     *
     * @param did
     * @return
     */
    @Override
    public boolean assignOrdersIsNo(String did) {
        Decidedzone decidedZone = decidedzoneDao.getDecidedZoneById(Integer.parseInt(did));
        List<StaffOrder> associationsOrder = staffOrderDao.findAssociationsOrder(decidedZone.getStaff().getId(), decidedZone.getRegion().getAreasId());
        for (StaffOrder staffOrder : associationsOrder) {
            System.out.println("staffOrder = " + staffOrder);
        }
        if (associationsOrder.isEmpty()) {//没有关联
            return true;
        }else {
            return false;
        }
    }

    /**
     * 删除
     *
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
    public boolean changDecidedzone(String decidedName, String staffId, String areaId, String selectId) {
        int id = Integer.parseInt(selectId);
        return decidedzoneDao.updateDecidedZone(areaId, staffId, decidedName, id);
    }

    /**
     * 去重
     *
     * @param decidedName
     * @return
     */
    @Override
    public Decidedzone getDecidedZone(String decidedName) {
        return decidedzoneDao.getDecidedZone(decidedName);
    }


}
