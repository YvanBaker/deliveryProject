package com.delivery.dao;

import com.delivery.entity.CustomerAddress;
import com.delivery.mapper.CustomerAddressDaoMapper;
import com.delivery.model.PickupAddress;
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
    @Select("select id, name,province_id, city_id, area_id, address_detail, user_id, del " +
            "from customer_address where user_id = #{userId}")
    List<CustomerAddress> selectCustomerAddressByIdUserId(@Param("userId") int userId);


    /**
     * 更新 userAddress
     *
     * @param customerAddress customerAddress
     * @return int 条数
     */
    @UpdateProvider(type = CustomerAddressDaoMapper.class, method = "updateCustomerAddressSql")
    int updateCustomerAddress(CustomerAddress customerAddress);

    /**
     * 根据 id del 查询
     *
     * @param userId id
     * @param del    del
     * @return List<CustomerAddress>
     */
    @Select("select id, name,province_id, city_id, area_id, address_detail, user_id, del " +
            "from customer_address where user_id = #{id} and del = #{del}")
    List<CustomerAddress> selectCustomerAddressByUserIdAndDel(@Param("id") int userId, @Param("del") int del);

    /**
     * 根据 id 查询
     *
     * @param id id
     * @return CustomerAddress
     */
    @Select("select id, name,province_id, city_id, area_id, address_detail, user_id, del " +
            "from customer_address where id = #{id}")
    CustomerAddress selectCustomerAddressById(@Param("id") int id);

    /**
     * 查询 有效用户地址 详细地址
     *
     * @param userId 用户id
     * @return List<PickupAddress>
     */
    @Select("select id, name, city_name, areas_name, province_name, province_id, area_id, city_id, address_detail, user_id, del " +
            "from pickup_address where user_id = #{id} and del = 0")
    List<PickupAddress> selectEffectivePickAddressByUserId(@Param("id") int userId);

    /**
     * 查询 有效的收货地址 详细地址
     * @param userId 用户id
     * @return List<PickupAddress>
     */
    @Select("select id, city_name, name, areas_name, province_name, province_id, area_id, city_id, address_detail, user_id, del " +
            "from receive_address_view where user_id = #{id} and del =0")
    List<PickupAddress> selectEffectiveReceiveAddressByUserId(@Param("id") int userId);
}
