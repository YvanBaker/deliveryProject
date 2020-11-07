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
     *
     * @return
     */
    List<Customer> getNoAssociationsCustomer();

    /**
     * 查询所有定区
     *
     * @return
     */
    List<Subarea> findAllSubarea();


    /**
     * 显示定区
     *
     * @return
     */
    PageUtil getDecidedZoneAll(int page, int rows);

    /**
     * 关联区
     *
     * @return
     */
    List<Region> associationsRegionAjax(String q);

    /**
     * 添加定区
     *
     * @param areaId
     * @param staffId
     * @param decidedName
     * @return
     */
    boolean addDecidedzone(String decidedName , String staffId, String areaId);

    /**
     * 查询单条数据
     * @param regionId
     * @return
     */
    List<Decidedzone> selectOneDecidedzone(String regionId);

    /**
     * 修改
     *
     * @param areaId
     * @param staffId
     * @param decidedName
     * @param selectId
     * @return
     */
    boolean changDecidedzone(String decidedName , String staffId, String areaId, String selectId);

    /**
     * 删除
     *
     * @param s
     * @return
     */
    boolean delectDecidedzone(String s);


    /**
     * 验证名字去重  查到不为null
     *
     * @param decidedName
     * @return
     */
    Decidedzone getDecidedZone(String decidedName);

    /**
     * 改进的分页查询并模糊查询定区
     *
     * @param page    第几页
     * @param rows    行数
     * @param name    定区名字
     * @param station 所属公司
     * @return
     */
    PageUtil getDecidedZoneShow(int page, int rows, String name, String station);

    /**
     * 是否有关联，为true 表示没有关联
     * @param did
     * @return
     */
    boolean assignOrdersIsNo(String did);
}
