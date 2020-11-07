package com.delivery.service.impl;

import com.delivery.dao.RegionArgDao;
import com.delivery.entity.RegionArg;
import com.delivery.service.RegionArgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wenJ
 * @Description 三级联动
 * @Classname RegionArgServiceImpl
 * @Date 2020/11/7 21:15
 */
@Service
public class RegionArgServiceImpl implements RegionArgService {
    @Resource
    RegionArgDao regionArgDao;

    @Override
    public List<RegionArg> getList() {
        return regionArgDao.getProvinceList();
    }
    @Override
    public List<RegionArg> getCityList(String parentid) {
        return regionArgDao.getCityList(parentid);
    }
    @Override
    public List<RegionArg> getAreaList(String parentid) {
        return regionArgDao.getAreaList(parentid);
    }
}
