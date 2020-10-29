package com.delivery.service.impl;

import com.delivery.dao.StaffDao;
import com.delivery.entity.Staff;
import com.delivery.service.StaffService;
import com.delivery.util.PageUtil;
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

    @Override
    public boolean addStaff(Staff staff) {
        return staffDao.addStaff(staff);
    }

    @Override
    public boolean removeStaff(Staff staff) {
        return staffDao.removeStaff(staff);
    }

    @Override
    public boolean updateStaff(Staff staff) {
        return staffDao.updateStaff(staff);
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
            total = staffDao.staffDelIsYCount();
        }else {
            findStaff.setPage((findStaff.getPage()-1)*findStaff.getRows());
            staffs =staffDao.selectStaffDeltagIsYesByBim(findStaff);
            total =staffDao.staffDelIsYCountByBim(findStaff);
        }
        return new PageUtil(total,staffs);
    }


}
