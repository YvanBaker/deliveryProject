package com.delivery.dao;

import com.delivery.entity.Instead;

import java.util.List;

/**
 * Created by LEO15 on 2020/10/23.
 * @author fujianian
 */
public interface InsteadDao {
    /**
     * 添加替班信息
     * @param instead instead
     * @return boolean
     */
    boolean addInstead(Instead instead);

    /**
     * 更新和删除替班信息
     * @param instead instead
     * @return boolean
     */
    boolean updateAndRemoveInstead(Instead instead);

    /**
     * 查询替班信息通过状态和被替人工号
     * @param status status
     * @param replacedStaffUuid repladedStaffUuid
     * @return list
     */
    List<Instead> selectInstead(int status, String replacedStaffUuid);

}
