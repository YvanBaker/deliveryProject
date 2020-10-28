package com.delivery.service.impl;

import com.delivery.dao.RegionDao;
import com.delivery.entity.Region;
import com.delivery.service.RegionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Resource
    private RegionDao regionDao;

    /**
     * 查询所有区域
     * @return
     */
    @Override
    public List<Region> getRegions() {
        return regionDao.getRegions();
    }

    @Override
    public List<Region> getRegionsByDim(String q) {
        return regionDao.getRegionsByDim(q);
    }

}
