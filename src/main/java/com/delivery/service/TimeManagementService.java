package com.delivery.service;

import com.delivery.entity.TimeManagement;
import com.delivery.util.PageUtil;
import com.delivery.utilentity.FindTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * Created by LEO15 on 2020/11/2.
 */

public interface TimeManagementService {
    /**
     * 添加时间管理信息
     * @param timeManagement timeManagement
     * @return boolean
     */
    boolean addTimeManagement(TimeManagement timeManagement) throws ParseException;

    /**
     * 修改时间管理信息
     * @param timeManagement timeManagement
     * @return boolean
     */
    boolean updateTimeManagement(TimeManagement timeManagement);

    /**
     * 删除时间管理信息
     * @param timeManagement timeManagement
     * @return boolean
     */
    boolean removeTimeManagement(TimeManagement timeManagement);

    /**
     * 查询全部上班时间信息
     * @return list
     */
    PageUtil selectTimeManagement(FindTime findTime);

}
