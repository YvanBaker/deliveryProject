package com.delivery.dao;

import com.delivery.entity.Region;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LEO15 on 2020/10/27.
 */
public interface RegionDao {
    /**
     * 查询全部关联分区
     * @return list
     */
    List<Region> selectAllRegion();

    /**
     * 分页 总数
     */
    int regionCount();

    List<Region> selectRegionLimit(int page, int rows);
    List<Region> regionLikP(@Param("q") String q);
    //boolean updateRegion(Region region);

    /**
     * 自动分单调用
     * 查询一条地区，
     * 用于判断手动录入订单的地区
     * @param region
     * @return
     */
    Region selectOneRegion(Region region);

    /**
     *自动分单调用
     *
     * @param areaId
     * @return
     */
    Region selectOneRegionByAreaId(String areaId);

    Region findRegionByAreaId(String areaId);
}
