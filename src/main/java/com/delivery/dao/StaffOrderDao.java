package com.delivery.dao;

import com.delivery.entity.Staff;
import com.delivery.entity.StaffOrder;
import com.delivery.mapper.StaffOrderDaoMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Yvan
 * @Description staff_order 表 id
 * @Classname StaffOrderDao
 * @Date 2020/11/3 13:46
 */
public interface StaffOrderDao {

    /**
     * 插入 工人 任务
     *
     * @param order 工单
     * @return int
     */
    @InsertProvider(type = StaffOrderDaoMapper.class, method = "insertOrderSql")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertOrder(StaffOrder order);

    /**
     * 更新 工单
     *
     * @param order 工单
     * @return 条数
     */
    @UpdateProvider(type = StaffOrderDaoMapper.class, method = "updateOrderSql")
    int updateOrder(StaffOrder order);

    /**
     * 根据 id 更新 staff_id 字段
     *
     * @param id      id
     * @param staffId staff_id
     * @return int
     */
    @Update("update staff_order set staff_id = #{staffId} where id = #{id}")
    int updateOrderStaffId(@Param("id") int id, @Param("staffId") int staffId);

    /**
     * 根据id 更新 状态 字段
     *
     * @param id  id
     * @param del del
     * @return int
     */
    @Update("update staff_order set del = #{del} where id = #{id}")
    int updateOrderDel(@Param("id") int id, @Param("del") int del);

    /**
     * 根据 工人 id 查询数据
     *
     * @param staff 工人
     * @return List<StaffOrder>
     */
    @Select("select id, staff_id, order_id from staff_order where staff_id = #{id}")
    List<StaffOrder> selectOrderByStaff(Staff staff);

    /**
     * 根据 工人 id 查询数据
     *
     * @param staffId 工人id
     * @return List<StaffOrder>
     */
    @Select("select id, staff_id, order_id from staff_order where staff_id = #{id}")
    List<StaffOrder> selectOrderByStaff(@Param("id") int staffId);

    /**
     * 更加 工单 id 查询 数据
     *
     * @param orderId 工单 id
     * @return StaffOrder
     */
    @Select("select id, staff_id, order_id from staff_order where order_id = #{id}")
    StaffOrder selectOrderByOrderId(@Param("id") int orderId);
}
