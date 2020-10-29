package com.delivery.service;

import com.delivery.entity.Standard;
import com.delivery.util.PageUtil;
import com.delivery.utilentity.FindStandard;

import java.util.List;

/**
 * Created by LEO15 on 2020/10/21.
 */
public interface StandardService {
    /**
     * 添加标准
     * @param standard standard
     * @return boolean
     */
    boolean addStandard(Standard standard);
    /**
     * 删除标准
     * @param standard standard
     * @return boolean
     */
    boolean removeStandardStatus(Standard standard);
    /**
     * 更新标准
     * @param standard standard
     * @return boolean
     */
    boolean updateStandard(Standard standard);

    /**
     * 条件查询标准
     * @return List
     */
    PageUtil selectStandardByElements(FindStandard findStandard);



    /**
     * 标准分页
     * @param page
     * @param rows
     * @return list
     */
    List<Standard> selectStandardLimit(int page, int rows);
    int standardCount();
}
