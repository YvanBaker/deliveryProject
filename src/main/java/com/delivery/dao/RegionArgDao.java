package com.delivery.dao;

import com.delivery.entity.RegionArg;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wenJ
 * @Description
 * @Classname RegionArgDao
 * @Date 2020/11/7 21:16
 */
@Repository
public interface RegionArgDao {
    List<RegionArg> getProvinceList();

    List<RegionArg> getCityList(String provinceId);

    List<RegionArg> getAreaList(String cityId);
}
