package com.delivery.dao;

import com.delivery.entity.Region;
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
    //boolean updateRegion(Region region);

}
