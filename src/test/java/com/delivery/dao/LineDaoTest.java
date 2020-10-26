package com.delivery.dao;

import com.delivery.entity.Line;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.assertTrue;


/**
 * Created by LEO15 on 2020/10/23.
 * @author fujianian
 * @Classname LineDaoTest
 * @Date 2020/10/23 13:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LineDaoTest {
    @Resource
    private LineDao lineDao;

    @Test
    public void testAddLine(){
        Line line = new Line();
        line.setLineType("省/市内");
        line.setLineName("松江—闵行");
        line.setLicensePlate("沪Bxxxxx");
        line.setCarModel("卡车");
        line.setDriver(1);
        line.setWeightControl(2.3);
        line.setOperator("test");
        line.setoStation("test");
        line.setoTime("test");
        boolean i=lineDao.addLine(line);
        assertTrue(i);
    }

    @Test
    public void testUpdateLine(){
        Line line=new Line();
        line.setLineType("干线1");
        line.setLineName("仓库2-仓库3");
        line.setDriver(2);
        line.setOperator("test1");
        line.setoStation("test1");
        line.setoTime("test1");
        line.setId(1);
        boolean i = lineDao.updateAndRemoveLine(line);
        assertTrue(i);
    }

    @Test
    public void testRemoveLine(){
        Line line =new Line();
        line.setId(1);
        line.setStatus(0);
        line.setOperator("test");
        line.setoStation("test");
        line.setoTime("test");
        boolean i =lineDao.updateAndRemoveLine(line);
        assertTrue(i);
    }

    @Test
    public void testSelectLine(){
        List<Line> list =lineDao.selectLine();
        assertTrue(list.size()>0);
    }

}
