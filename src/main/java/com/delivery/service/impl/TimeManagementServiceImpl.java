package com.delivery.service.impl;

import com.delivery.dao.TimeManagementDao;
import com.delivery.entity.TimeManagement;
import com.delivery.service.TimeManagementService;
import com.delivery.util.PageUtil;
import com.delivery.util.TimeUtil;
import com.delivery.utilentity.FindTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * Created by LEO15 on 2020/11/2.
 */
@Service
public class TimeManagementServiceImpl implements TimeManagementService {

    @Resource
    private TimeManagementDao timeManagementDao;

    @Override
    public boolean addTimeManagement(TimeManagement timeManagement) throws ParseException {
        if(null==timeManagement){
            return false;
        }
        /*
        * 判断时间
        */
        boolean flag1 = TimeUtil.timeReduce(timeManagement.getNormalOffWorkTime(),timeManagement.getNormalWorkTime());
        boolean flag2 = TimeUtil.timeReduce(timeManagement.getWeekOffWorkTime(),timeManagement.getWeekWorkTime());
        if (flag1&&flag2){
            /*
            * 判断重复
            */
            TimeManagement t=timeManagementDao.selectTimeManagementByStationAndTimeName(timeManagement);
            if (null==t){
                return timeManagementDao.addTimeManagement(timeManagement);
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public boolean updateTimeManagement(TimeManagement timeManagement) {
        if(null==timeManagement){
            return false;
        }
        return timeManagementDao.updateTimeManagement(timeManagement);
    }

    @Override
    public boolean removeTimeManagement(TimeManagement timeManagement) {
        if(null==timeManagement){
            return false;
        }
        return timeManagementDao.removeTimeManagement(timeManagement);
    }

    @Override
    public PageUtil selectTimeManagement(FindTime findTime) {
        List<TimeManagement> timeManagementList=timeManagementDao.selectTimeManagement(findTime);
        int i=timeManagementDao.selectCount();
        return new PageUtil(i,timeManagementList);
    }
}
