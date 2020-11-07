package com.delivery.service.impl;

import com.delivery.dao.LineDao;
import com.delivery.dao.StaffDao;
import com.delivery.dao.StaffOrderDao;
import com.delivery.entity.Line;
import com.delivery.entity.Staff;
import com.delivery.entity.StaffOrder;
import com.delivery.service.LineService;
import com.delivery.util.PageUtil;
import com.delivery.utilentity.FindLine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LEO15 on 2020/10/30.
 */
@Service
public class LineServiceImpl implements LineService {
@Resource
private LineDao lineDao;
@Resource
private StaffDao staffDao;
@Resource
private StaffOrderDao staffOrderDao;

    @Override
    public PageUtil selectLine(FindLine findLine) {
        List<Line> lineList=lineDao.selectLine(findLine);
        int i=lineDao.selectLineCount();
        return new PageUtil(i,lineList);
    }

    @Override
    public int lineCount() {
        return lineDao.selectLineCount();
    }

    @Override
    public boolean addLine(Line line) {
        return lineDao.addLine(line);
    }

    @Override
    public boolean updateLine(Line line) {
        return lineDao.updateLine(line);
    }

    @Override
    public boolean removeLine(Line line) {
        int lineId=line.getId();
        Line line1=lineDao.selectLineByID(lineId);
        String staffName=line1.getDriver();
        Staff staff=staffDao.selectStaffByName(staffName);
        int staffId=staff.getId();
        List<StaffOrder> staffOrders=staffOrderDao.selectOrderByStaffId(staffId);
        for (int i=0;i<staffOrders.size();i++){
            if (staffOrders.get(i).getDel()!=2){
                return false;
            }
        }
        return lineDao.removeLine(line);
    }

}
