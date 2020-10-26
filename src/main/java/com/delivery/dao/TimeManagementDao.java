package com.delivery.dao;

import com.delivery.entity.TimeManagement;

import java.util.List;

/**
 * Created by LEO15 on 2020/10/26.
 * @author fujianian
 */
public interface TimeManagementDao {
    /**
     * 添加时间管理信息
     * @param timeManagement timeManagement
     * @return boolean
     */
    boolean addTimeManagement(TimeManagement timeManagement);

    /**
     * 更新和删除时间管理信息
     * @param timeManagement timeManagement
     * @return boolean
     */
    boolean updateAndRemoveTimeManagement(TimeManagement timeManagement);

    /**
     * 查询全部上班时间信息
     * @return list
     */
    List<TimeManagement> selectTimeManagement();
}
