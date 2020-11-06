package com.delivery.service.impl;

import com.delivery.dao.RegionDao;
import com.delivery.dao.SubareaDao;
import com.delivery.entity.Region;
import com.delivery.entity.Subarea;
import com.delivery.service.AddressService;
import com.delivery.service.RegionService;
import com.delivery.util.PageUtil;
import com.delivery.utilentity.ShowSubarea;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by LEO15 on 2020/10/27.
 */
@Service
public class RegionServiceImpl implements RegionService {
    @Resource
    private RegionDao regionDao;

    @Resource
    private SubareaDao subareaDao;

    @Resource
    AddressService addressService;


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
  
    /**
     * 分区动态查询
     * @param q
     * @return
     */
    @Override
    public List<Region> findAllRegionLikP(String q) {
        q = "%" + q + "%";
        return regionDao.regionLikP(q);
    }

    /**
     * 存入分区
     * @param subarea
     * @return
     */
    @Override
    public boolean insertSubarea(Subarea subarea) {
        return subareaDao.insertSubarea(subarea);
    }

    /**
     * 分页展示分区
     * @param showSubarea
     * @return
     */
    @Override
    public PageUtil selectSubarealimit(ShowSubarea showSubarea) {
        showSubarea.setPage(showSubarea.getRows()*(showSubarea.getPage()-1));
        List<Subarea> subareas = new ArrayList<>();
        int total = 0;
        if (showSubarea.isDim()) {
            if (showSubarea.getAddresskey() != "" && showSubarea.getAddresskey() != null) {
                showSubarea.setAddresskey("%" + showSubarea.getAddresskey() + "%");
            }
            if (showSubarea.getRegion().getProvinceName() != "" && showSubarea.getRegion().getProvinceName() != null) {
                showSubarea.getRegion().setProvinceName("%"+showSubarea.getRegion().getProvinceName()+"%");
            }
            if (showSubarea.getRegion().getCityName() != "" && showSubarea.getRegion().getCityName() != null) {
                showSubarea.getRegion().setCityName("%"+showSubarea.getRegion().getCityName()+"%");
            }
            if (showSubarea.getRegion().getAreasName() != "" && showSubarea.getRegion().getAreasName() != null) {
                showSubarea.getRegion().setAreasName("%"+showSubarea.getRegion().getAreasName()+"%");
            }
            subareas = subareaDao.getSubareaLimitDim(showSubarea);
            total= subareaDao.getSubareaTotalDim(showSubarea);
        }else {
             subareas= subareaDao.getSubareaLimit(showSubarea);
             total= subareaDao.getSubareaTotal();
        }
        return new PageUtil(total,subareas);
    }
}
