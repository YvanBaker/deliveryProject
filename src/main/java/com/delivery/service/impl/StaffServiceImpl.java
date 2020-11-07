package com.delivery.service.impl;

import com.delivery.dao.*;
import com.delivery.entity.*;
import com.delivery.service.StaffService;
import com.delivery.util.PageUtil;
import com.delivery.util.TimeUtil;
import com.delivery.utilentity.FindStaff;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LEO15 on 2020/10/19.
 */
@Service
public class StaffServiceImpl implements StaffService {
    @Resource
    private StaffDao staffDao;
    @Resource
    private StandardDao standardDao;
    @Resource
    private StaffOrderDao staffOrderDao;
    @Resource
    private DecidedzoneDao decidedzoneDao;
    @Resource
    private LineDao lineDao;

    @Override
    public boolean addStaff(Staff staff) {
        //添加时，判断标准名称是否存在
        String standardName=staff.getStandard();
        List<Standard> standardList= standardDao.selectStandardByStandardName(standardName);
        if(standardList.size()<0){
            return false;
        }else{
            return staffDao.addStaff(staff);
        }
    }

    @Override
    public boolean removeStaff(Staff staff) {
        //判断订单中是否有此人 未完成的订单
        int staffId=staff.getId();
        String staffid=Integer.toString(staffId);
        List<Staff> staffList=staffDao.selectStaffByOneElement(staff);
        String staffName = staffList.get(0).getName();
        List<StaffOrder> staffOrderList=staffOrderDao.selectOrderForRemoveStaff(staffId);
        if (staffOrderList.size()>0){
            return false;
        }
        //判断是否与定区关联，关联置空
        List<Decidedzone> list =decidedzoneDao.selectDecidedZoneForRemoveStaff(staffid);
        if(list.size()>0){
            for (int i=0;i<list.size();i++){
                int decidedzoneId=list.get(1).getId();
                decidedzoneDao.updateDecidedZoneForStaffToNull(decidedzoneId);
            }
        }
        //判断班车信息是否与之关联，关联置空
        Line line= lineDao.selectLineForRemoveStaff(staffName);
        if(line!=null){
            Line newLine=new Line();
            newLine.setId(line.getId());
            newLine.setDriver("null");
            newLine.setoTime(TimeUtil.localtime());
            lineDao.updateLine(newLine);
        }
        if(staffDao.removeStaff(staff)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateStaff(Staff staff) {
        String standardName=staff.getStandard();
        List<Standard> standardList= standardDao.selectStandardByStandardName(standardName);
        if(standardList.size()<0){
            return false;
        }else{
            return staffDao.updateStaff(staff);
        }
    }

    @Override
    public List<Staff> selectStaffDeltagIsYes() {
        return staffDao.selectStaffDeltagIsYes();
    }

    @Override
    public List<Staff> selectAllStaff() {
        return staffDao.selectAllStaff();
    }


    @Override
    public List<Staff> selectStaffByOneElement(Staff staff) {
        return staffDao.selectStaffByOneElement(staff);
    }

    /**
     * 查询 模糊查询
     *
     * @param findStaff
     * @return
     */
    @Override
    public PageUtil selectStaff(FindStaff findStaff) {
        int total = 0;
        List<Staff> staffs = new ArrayList<>();
        if (findStaff.isNull()) {//查询所有
            staffs = staffDao.selectStafflimit((findStaff.getPage()-1)*findStaff.getRows(),findStaff.getRows());
            total = staffDao.staffCount();
        }else {
            findStaff.setPage((findStaff.getPage()-1)*findStaff.getRows());
            staffs =staffDao.selectStaffByDim(findStaff);
            total =staffDao.staffCountByDim(findStaff);
        }
        return new PageUtil(total,staffs);
    }

    /**
     * 恢复取派员
     * @param staff
     * @return
     */
    @Override
    public boolean renewStaff(Staff staff) {
        return staffDao.staffRenew(staff);
    }


}
