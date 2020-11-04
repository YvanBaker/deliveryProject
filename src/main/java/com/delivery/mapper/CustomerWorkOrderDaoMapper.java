package com.delivery.mapper;

import com.delivery.entity.CustomerWorkOrder;
import com.delivery.util.TypeUtil;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Yvan
 * @Description CustomerWorkOrder 的 mapper
 * @Classname CustomerWorkOrderDaoMapper
 * @Date 2020/10/30 11:04
 */

public class CustomerWorkOrderDaoMapper {

    /**
     * 表名
     */
    private final String TABLE_NAME = "customer_work_order";

    /**
     * 插入语句的sql
     *
     * @return String sql
     */
    public String insertOrderSql(CustomerWorkOrder order) {
        return new SQL() {
            {
                INSERT_INTO(TABLE_NAME);
                if (order.getWorkOrderUuid() != null && TypeUtil.isValidString(order.getWorkOrderUuid())) {
                    VALUES("work_order_uuid", "#{workOrderUuid}");
                }
                if (order.getCustomerId() != null) {
                    VALUES("customer_id", "#{customerId}");
                }
                if (order.getPickupAddressId() != null) {
                    VALUES("pickup_address_id", "#{pickupAddressId}");
                }
                if (order.getReceiveAddressId() != null) {
                    VALUES("receive_address_id", "#{receiveAddressId}");
                }
                if (order.getCreateTime() != null) {
                    VALUES("create_time", "#{createTime}");
                }
                if (order.getConsummation() != null) {
                    VALUES("consummation", "#{consummation}");
                }
            }
        }.toString();
    }

    /**
     * 更新 sql
     *
     * @param order 订单
     * @return String sql
     */
    public String updateOrderSql(CustomerWorkOrder order) {
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                if (order.getWorkOrderUuid() != null &&
                        TypeUtil.isValidString(order.getWorkOrderUuid())) {
                    SET("word_order_uuid = #{workOrderUuid}");
                }
                if (order.getCustomerId() != null) {
                    SET("customer_id #{customerId}");
                }
                if (order.getPickupAddressId() != null) {
                    SET("pickup_address_id = #{pickupAddressId}");
                }
                if (order.getReceiveAddressId() != null){
                    SET("receive_address_id = #{receiveAddressId}");
                }
                if (order.getConsummation() != null){
                    SET("consummation = #{consummation}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }

    public String selectOrderBySql(CustomerWorkOrder order) {
        return new SQL(){
            {
                SELECT("id, work_order_uuid, customer_id, pickup_address_id, receive_address_id, create_time, consummation");
                FROM(TABLE_NAME);
            }
        }.toString();
    }
}
