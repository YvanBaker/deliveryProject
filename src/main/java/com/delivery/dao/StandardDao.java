package com.delivery.dao;

import com.delivery.entity.Standard;
import com.delivery.utilentity.FindStandard;

import java.util.List;

/**
 * Created by LEO15 on 2020/10/21.
 * @author fujianian
 * @Description Standard(派送标准) 表的Dao层
 * @Classname StandardDao
 * @Date 2020/10/21
 */
public interface StandardDao {
    /**
     * 添加标准
     * @param standard
     * @return boolean
     */
    boolean addStandard(Standard standard);
    /**
     * 删除标准
     * @param standard
     * @return boolean
     */
    boolean removeStandard(Standard standard);
    /**
     * 修改标准
     * @param standard
     * @return boolean
     */
    boolean updateStandard(Standard standard);
    /**
     * 条件查询标准
     * @return list
     */
    List<Standard> selectStandardByElements(FindStandard findStandard);
    /*
     * 查询使用中/已经删除的标准
     * @param status
     * @return list
     */
    //List<Standard> selectStandardByStatus(int status);
    /**
     * 按照名字查询标准
     * @param  standardName
     * @return list
     */
    List<Standard> selectStandardByStandardName(String standardName);

    /**
     * 标准，分页
     * @param page
     * @param rows
     * @return list
     */
    List<Standard> selectStandardLimit(int page, int rows);
    int standardCount();
}
