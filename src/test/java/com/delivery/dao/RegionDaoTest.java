package com.delivery.dao;

import com.delivery.entity.Region;
import com.delivery.entity.Subarea;
import com.delivery.util.PageUtil;
import com.delivery.utilentity.ShowSubarea;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertFalse;


/**
 * Created by LEO15 on 2020/10/27.\
 * @author fujianian
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RegionDaoTest {
    @Resource
    private RegionDao regionDao;

    @Resource
    private SubareaDao subareaDao;
    @Test
    public void testSelect(){
        List<Region> list=regionDao.selectAllRegion();
        for (Region r:list){
            System.out.println(r);
        }
        assertFalse(list.isEmpty());
    }

    @Test

    public void selectOneRegion(){
        Region r=regionDao.selectOneRegion(new Region("北京市","市辖区","东城区"));
        assertFalse(r==null);
    }
  
    public void findAllRegionLikPtest() {
        String q="";
        List<Region> regions = regionDao.regionLikP(q);
        regions.forEach(System.out::println);
        assertFalse(regions.isEmpty());
    }

    @Test
    public void testSub() {
        ShowSubarea showSubarea = new ShowSubarea();
        showSubarea.setPage(0);
        showSubarea.setRows(10);
        Region region = new Region();
        region.setProvinceName("天");
        showSubarea.setRegion(region);
        int subareaTotalDim = subareaDao.getSubareaTotalDim(showSubarea);
        List<Subarea> subareaLimitDim = subareaDao.getSubareaLimitDim(showSubarea);
        System.out.println("subareaLimitDim = " + subareaLimitDim);
        System.out.println("subareaTotalDim = " + subareaTotalDim);
        assertFalse(true);
    }
}
