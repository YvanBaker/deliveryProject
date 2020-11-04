package com.delivery.dao;

import com.delivery.entity.CustomerWorkOrder;
import com.delivery.mapper.CustomerWorkOrderDaoMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yvan
 * @Description 用户工单 dao
 * @Classname CustomerWorkOrderDao
 * @Date 2020/10/30 11:02
 */
@Repository
public interface CustomerWorkOrderDao {

    /**
     * 插入 用户 工单
     *
     * @param order 工单
     * @return int
     */
    @InsertProvider(type = CustomerWorkOrderDaoMapper.class, method = "insertOrderSql")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertOrder(CustomerWorkOrder order);

    /**
     * 更新 订单
     *
     * @param newOrder 新订单
     * @return int
     */
    @InsertProvider(type = CustomerWorkOrderDaoMapper.class, method = "updateOrderSql")
    int updateOrder(CustomerWorkOrder newOrder);

    /**
     * 根据 订单类中 的 字段查询 （条件是 and）
     *
     * @param order 订单类
     * @return List<CustomerWorkOrder>
     */
    @SelectProvider(type = CustomerWorkOrderDaoMapper.class, method = "selectOrderBySql")
    List<CustomerWorkOrder> selectOrderBy(CustomerWorkOrder order);

    /**
     * 根据 id 查询
     *
     * @param id id
     * @return CustomerWorkOrder
     */
    @Select("select id, work_order_uuid, customer_id, pickup_address_id, receive_address_id, create_time, consummation " +
            "from customer_work_order where id = #{id}")
    CustomerWorkOrder selectOrderById(@Param("id") int id);

    /**
     * 根据 uuid 查询
     *
     * @param UUID uuid
     * @return CustomerWorkOrder
     */
    @Select("select id, work_order_uuid, customer_id, pickup_address_id, receive_address_id, create_time, consummation " +
            "from customer_work_order where work_order_uuid = #{uuid}")
    CustomerWorkOrder selectOrderByUUID(@Param("uuid") String UUID);

    /**
     * 根据 用户id 查询
     *
     * @param customerId 用户id
     * @return List<CustomerWorkOrder>
     */
    @Select("select id, work_order_uuid, customer_id, pickup_address_id, receive_address_id, create_time, consummation " +
            "from customer_work_order where customer_id = #{id}")
    List<CustomerWorkOrder> selectOrderByCustomerId(@Param("id") int customerId);

    /**
     * 根据 状态 查询
     *
     * @param consummation 状态
     * @return List<CustomerWorkOrder>
     */
    @Select("select * from customer_work_order where consummation = #{con}")
    List<CustomerWorkOrder> selectOrderByConsummation(@Param("con") int consummation);
}
