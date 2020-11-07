package com.delivery.service;

import com.delivery.entity.RegionArg;

import java.util.List;

/**
 * @author wenJ
 * @Description
 * @Classname RegionArgService
 * @Date 2020/11/7 21:12
 */
public interface RegionArgService {


    List<RegionArg> getList();

    List<RegionArg> getCityList(String parentid);

    List<RegionArg> getAreaList(String parentid);
}
