package com.delivery.service;

import com.delivery.entity.Decidedzone;
import com.delivery.entity.Customer;
import com.delivery.entity.Region;
import com.delivery.entity.Subarea;
import com.delivery.util.PageUtil;

import java.util.List;

/**
 * @author wenJ
 * @Description
 * @Classname DecidedzoneService
 * @Date 2020/10/31 15:38
 */
public interface DecidedzoneService {

    /**
     * 要关联的顾客
     * @return
     */
    List<Customer> getNoAssociationsCustomer();

    /**
     * 查询所有定区
     * @return
     */
    List<Subarea> findAllSubarea();

    /**
     * 添加定区
     * @param decidedzone
     * @return
     */
    boolean addDecidedzone(Decidedzone decidedzone);

    /**
     * 显示定区
     * @return
     */
    PageUtil getDecidedZoneAll(int page, int rows);

    /**
     * 关联区
     * @return
     */
    List<Region> associationsRegionAjax(String q);

    /**
     * 添加定区
     * @param areaId
     * @param staffId
     * @param decidedName
     * @return
     */
    boolean addDecidedzone(String areaId, String staffId, String decidedName);

    /**
     * 查询单条数据
     * @param regionId
     * @return
     */
    List<Decidedzone> selectOneDecidedzone(String regionId);

}
