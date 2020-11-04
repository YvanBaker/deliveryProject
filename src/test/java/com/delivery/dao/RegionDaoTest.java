package com.delivery.dao;

import com.delivery.entity.Region;
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
}
