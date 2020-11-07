package com.delivery.service;


import com.delivery.entity.Region;
import com.delivery.entity.Subarea;
import com.delivery.util.PageUtil;
import com.delivery.utilentity.ShowSubarea;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by LEO15 on 2020/10/27.
 * @author fujianian
 */
public interface RegionService {

    //List<Region> selectAllRegion();

    /**
     * 分页查询全部信息
     * @return collection
     */
    PageInfo<Region> findAllRegion();

    /**
     * 分页 总数
     */
    int regionCount();

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return list
     */
    List<Region> selectRegionLimit(int page, int rows);

    /**
     * 查询一条地区，
     * 用于手动录入订单判断地区
     * @param region
     * @return
     */
    Region selectOneRegion(Region region);

    /**
     * 区域下拉框信息ajax查询
     * @param q
     * @return
     */
    List<Region> findAllRegionLikP(String q);

    /**
     * 分区
     * @param subarea
     * @return
     */
    boolean insertSubarea(Subarea subarea);

    /**
     * 分页展示分区
     * @param showSubarea
     * @return
     */
    PageUtil selectSubarealimit(ShowSubarea showSubarea);

    Region findRegionByAreaId(String addressArea);
}
