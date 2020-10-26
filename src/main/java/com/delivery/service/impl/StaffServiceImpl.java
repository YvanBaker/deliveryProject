package com.delivery.service.impl;

import com.delivery.dao.StaffDao;
import com.delivery.entity.Staff;
import com.delivery.service.StaffService;
import com.delivery.utilentity.FindStaff;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
     * 通过 工号，所属定区，收派标准，所属单位查寻
     * @param findStaff
     * @return
     */
    @Override
    public Staff selectStaffByColumns(FindStaff findStaff) {
        return staffDao.selectStaffByColumns(findStaff);
    }

    @Override
    public List<Staff> selectStaffByPage(int page, int rows) {
        return staffDao.selectStafflimit((page-1)*rows,rows);
    }
    /**
     * 统计未删除的staff总数
     * @return
     */
    @Override
    public int staffDelIsYTotal() {
        return staffDao.staffDelIsYCount();
    }


}
