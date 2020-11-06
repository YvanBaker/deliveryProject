package com.delivery.mapper;

import com.delivery.entity.StaffOrder;
import com.delivery.util.TypeUtil;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Yvan
 * @Description StaffOrderDao 的 动态 sql 语句
 * @Classname StaffOrderDaoMapper
 * @Date 2020/11/3 13:59
 */
public class StaffOrderDaoMapper {

    private final String TABLE_NAME = "staff_order";

    public String insertOrderSql(StaffOrder order) {
        return new SQL() {
            {
                INSERT_INTO(TABLE_NAME);
                if (order.getStaffId() != null) {
                    VALUES("staff_id", "#{staffId}");
                }
                if (TypeUtil.isValidString(order.getOrderId())) {
                    VALUES("order_id", "#{orderId}");
                }
                if(TypeUtil.isValidString(order.getAreaId())){
                    VALUES("area_id","#{areaId}");
                }
                if (order.getDel()!=null){
                    VALUES("del","#{del}");
                }
            }
        }.toString();
    }

    public String updateOrderSql(StaffOrder order) {
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                if (order.getStaffId() != null) {
                    SET("staff_id = #{staffId}");
                }
                if (TypeUtil.isValidString(order.getOrderId())) {
                    SET("order_id = #{orderId}");
                }
                if (TypeUtil.isValidString(order.getAreaId())){
                    SET("area_id=#{areaId}");
                }
                if (order.getDel() != null) {
                    SET("del = #{del}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }
}
