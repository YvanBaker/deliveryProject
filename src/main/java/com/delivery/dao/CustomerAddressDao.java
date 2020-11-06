package com.delivery.dao;

import com.delivery.entity.CustomerAddress;
import com.delivery.mapper.CustomerAddressDaoMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Yvan
 * @Description user_address 表 dao 层
 * @Classname CustomerAddressDao
 * @Date 2020/10/21 15:21
 */
@Repository
public interface CustomerAddressDao {

    /**
     * 插入用户地址
     *
     * @param customerAddress 用户地址
     * @return int 条数
     */
    @InsertProvider(type = CustomerAddressDaoMapper.class, method = "insertCustomerAddressSql")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertCustomerAddress(CustomerAddress customerAddress);

    /**
     * 根据 userid 查询地址
     *
     * @param userId userid
     * @return List<UserAddress>
     */
    @Select("select id, province_id, city_id, area_id, address_detail, user_id from customer_address where user_id = #{userId}")
    List<CustomerAddress> selectCustomerAddressByIdUserId(@Param("userId") int userId);

    /**
     * 更新 userAddress
     *
     * @param customerAddress customerAddress
     * @return int 条数
     */
    @UpdateProvider(type = CustomerAddressDaoMapper.class, method = "updateCustomerAddressSql")
    int updateCustomerAddress(CustomerAddress customerAddress);

    @Select("select id,province_id,city_id,area_id,address_detail,user_id from customer_address where id = #{id} and del='0'")
   CustomerAddress selectOneCustomerAddressById(int id);
}
