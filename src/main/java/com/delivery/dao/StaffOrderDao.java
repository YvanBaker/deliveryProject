package com.delivery.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wenJ
 * @Description
 * @Classname StaffOrderDao
 * @Date 2020/11/5 10:46
 */
@Repository
public interface StaffOrderDao {

    /**
     * 添加关联
     * @param id
     * @param i
     * @param orderIds
     * @return
     */
    boolean addAssignOrders(@Param("id") String id,@Param("staffId") int i, @Param("orderIds") String orderIds);
}
