package com.delivery.mapper;

import com.delivery.entity.CustomerReceiveAddress;
import com.delivery.util.TypeUtil;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Yvan
 * @Description CustomerReceiveAddress mapper
 * @Classname CustomerReceiveAddressDaoMapper
 * @Date 2020/10/30 12:02
 */
public class CustomerReceiveAddressDaoMapper {

    private final String TABLE_NAME = "customer_receive_address";

    /**
     * 插入用户地址 sql
     *
     * @param address 地址
     * @return String sql
     */
    public String insertAddressSql(CustomerReceiveAddress address) {
        return new SQL() {
            {
                INSERT_INTO(TABLE_NAME);
                if (address.getCustomerId() != null) {
                    VALUES("customer_id", "#{customerId}");
                }
                if (address.getReceiveName() != null &&
                        TypeUtil.isValidString(address.getReceiveName())) {
                    VALUES("receive_name", "#{receiveName}");
                }
                if (address.getReceiveProvinceId() != null &&
                        TypeUtil.isValidString(address.getReceiveProvinceId())) {
                    VALUES("receive_province_id", "#{receiveProvinceId}");
                }
                if (address.getReceiveCityId() != null &&
                        TypeUtil.isValidString(address.getReceiveCityId())) {
                    VALUES("receive_city_id", "#{receiveCityId}");
                }
                if (address.getReceiveAreaId() != null &&
                        TypeUtil.isValidString(address.getReceiveAreaId())) {
                    VALUES("receive_area_id", "#{receiveAreaId}");
                }
                if (address.getReceiveDetailedAddress() != null &&
                        TypeUtil.isValidString(address.getReceiveDetailedAddress())) {
                    VALUES("receive_derailed_address", "#{receiveDetailedAddress}");
                }
            }
        }.toString();
    }

    /**
     * 修改 sql
     *
     * @param address 地址
     * @return String sql
     */
    public String updateAddressSql(CustomerReceiveAddress address) {
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                if (address.getReceiveName() != null &&
                        TypeUtil.isValidString(address.getReceiveName())) {
                    SET("receive_name = #{receiveName}");
                }
                if (TypeUtil.isValidString(address.getReceiveProvinceId())) {
                    SET("receive_province_id = #{receiveProvinceId}");
                }
                if (TypeUtil.isValidString(address.getReceiveCityId())) {
                    SET("receive_city_id = #{receiveCityId}");
                }
                if (TypeUtil.isValidString(address.getReceiveAreaId())) {
                    SET("receive_area_id = #{receiveAreaId}");
                }
                if (TypeUtil.isValidString(address.getReceiveDetailedAddress())) {
                    SET("receive_detailed_address = #{receiveDetailedAddress}");
                }
                if (address.getDel() != null) {
                    SET("del = #{del}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }

    /**
     * 查询 sql
     *
     * @param address 地址类
     * @return String sql
     */
    public String selectAddressBySql(CustomerReceiveAddress address) {
        return new SQL() {
            {
                SELECT("id, customer_id, receive_name, receive_province_id, receive_city_id, receive_area_id, receive_detailed_address, del");
                FROM(TABLE_NAME);
                if (address.getCustomerId() != null) {
                    WHERE("customer_id = #{customerId}");
                }
                if (TypeUtil.isValidString(address.getReceiveProvinceId())) {
                    WHERE("receive_province_id = #{receiveProvinceId}");
                }
                if (TypeUtil.isValidString(address.getReceiveName())) {
                    WHERE("receive_name = #{receiveName}");
                }
                if (TypeUtil.isValidString(address.getReceiveCityId())) {
                    WHERE("receive_city_id = #{receiveCityId}");
                }
                if (TypeUtil.isValidString(address.getReceiveAreaId())) {
                    WHERE("receive_area_id = #{receiveAreaId}");
                }
                if (address.getDel() != null) {
                    WHERE("del = #{del}");
                }
            }
        }.toString();
    }
}
