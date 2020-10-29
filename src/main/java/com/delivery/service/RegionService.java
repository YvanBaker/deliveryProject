package com.delivery.service;


import com.delivery.entity.Region;
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
}
