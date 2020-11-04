package com.delivery.service.impl;

import com.delivery.dao.RegionDao;
import com.delivery.entity.Region;
import com.delivery.service.RegionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by LEO15 on 2020/10/27.
 */
@Service
public class RegionServiceImpl implements RegionService {
    @Resource
    private RegionDao regionDao;

    @Override
    public PageInfo<Region> findAllRegion() {
        PageHelper.startPage(1,30);
        List<Region> regionList=regionDao.selectAllRegion();
        PageInfo<Region> pageInfo=new PageInfo<>(regionList);
        return pageInfo;
    }

    @Override
    public int regionCount() {
        return regionDao.regionCount();
    }

    @Override
    public List<Region> selectRegionLimit(int page, int rows) {
        return regionDao.selectRegionLimit((page-1)*rows,rows);
    }

    @Override
    public Region selectOneRegion(Region region) {
        return regionDao.selectOneRegion(region);
    }
}
