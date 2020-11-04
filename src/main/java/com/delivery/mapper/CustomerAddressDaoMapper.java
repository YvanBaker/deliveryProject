package com.delivery.mapper;

import com.delivery.entity.CustomerAddress;
import com.delivery.util.TypeUtil;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Yvan
 * @Description CustomerAddressDao 中 用到的动态sql语言
 * @Classname UserAddressDaoMapper
 * @Date 2020/10/21 16:26
 */
public class CustomerAddressDaoMapper {

    /**
     * 插入用户地址的sql 语句
     *
     * @param customerAddress 用户地址
     * @return String sql
     */
    public String insertCustomerAddressSql(CustomerAddress customerAddress) {
        return new SQL() {
            {
                INSERT_INTO("customer_address");
                if (customerAddress.getProvinceId() != null &&
                        TypeUtil.isValidString(customerAddress.getProvinceId())) {
                    VALUES("province_id", "#{provinceId}");
                }
                if (customerAddress.getCityId() != null &&
                        TypeUtil.isValidString(customerAddress.getCityId())) {
                    VALUES("city_id", "#{cityId}");
                }
                if (customerAddress.getAreaId() != null && TypeUtil.isValidString(customerAddress.getAreaId())) {
                    VALUES("area_id", "#{areaId}");
                }
                if (customerAddress.getAddressDetail() != null &&
                        TypeUtil.isValidString(customerAddress.getAddressDetail())) {
                    VALUES("address_detail", "#{addressDetail}");
                }
                if (customerAddress.getUserId() != null) {
                    VALUES("user_id", "#{userId}");
                }
            }
        }.toString();
    }

    /**
     * 更新 用户地址 的动态sql 语句
     *
     * @param customerAddress 用户地址
     * @return String sql
     */
    public String updateCustomerAddressSql(CustomerAddress customerAddress) {
        return new SQL() {
            {
                UPDATE("customer_address");
                if (customerAddress.getProvinceId() != null &&
                        TypeUtil.isValidString(customerAddress.getProvinceId())) {
                    SET("province_id = #{provinceId}");
                }
                if (customerAddress.getCityId() != null &&
                        TypeUtil.isValidString(customerAddress.getCityId())) {
                    SET("city_id = #{cityId}");
                }
                if (customerAddress.getAreaId() != null &&
                        TypeUtil.isValidString(customerAddress.getAreaId())) {
                    SET("area_id = #{areaId}");
                }
                if (customerAddress.getAddressDetail() != null &&
                        TypeUtil.isValidString(customerAddress.getAddressDetail())) {
                    SET("address_detail = #{addressDetail}");
                }
                if (customerAddress.getDel() != null) {
                    SET("del = #{del}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }
}
