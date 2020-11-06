package com.delivery.dao;

import com.delivery.entity.CustomerReceiveAddress;
import com.delivery.mapper.CustomerReceiveAddressDaoMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yvan
 * @Description 用户货物送达地址表 dao层
 * @Classname CustomerReceiveAddressDao
 * @Date 2020/10/30 11:55
 */
@Repository
public interface CustomerReceiveAddressDao {

    /**
     * 插入送达地址
     *
     * @param address 地址
     * @return int
     */
    @InsertProvider(type = CustomerReceiveAddressDaoMapper.class, method = "insertAddressSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertAddress(CustomerReceiveAddress address);

    /**
     * 修改地址
     *
     * @param newAddress 新地址
     * @return int
     */
    @UpdateProvider(type = CustomerReceiveAddressDaoMapper.class, method = "updateAddressSql")
    int updateAddress(CustomerReceiveAddress newAddress);

    /**
     * 根据 送达地址类 查询
     *
     * @param address 送达地址
     * @return List<CustomerReceiveAddress>
     */
    @SelectProvider(type = CustomerReceiveAddressDaoMapper.class, method = "selectAddressBySql")
    List<CustomerReceiveAddress> selectAddressBy(CustomerReceiveAddress address);

    /**
     * 根据 用户 id 查询
     *
     * @param customerId id
     * @return List<CustomerReceiveAddress>
     */
    @Select("select id, customer_id, receive_name, receive_province_id, receive_city_id, receive_area_id, receive_detailed_address, del " +
            "from customer_receive_address where customer_id = #{id}")
    List<CustomerReceiveAddress> selectAddressByCustomerId(@Param("id") int customerId);

    /**
     * 根据 id 查询
     *
     * @param id id
     * @return CustomerReceiveAddress
     */
    @Select("select id, customer_id, receive_name, receive_province_id, receive_city_id, receive_area_id, receive_detailed_address, del " +
            "from customer_receive_address where id = #{id}")
    CustomerReceiveAddress selectAddressesById(@Param("id") int id);

    /**
     * 根据 状态 查询
     *
     * @param customerId 用户 id
     * @param del 状态
     * @return List<CustomerReceiveAddress>
     */
    @Select("select id, customer_id, receive_name, receive_province_id, receive_city_id, receive_area_id, receive_detailed_address, del " +
            "from customer_receive_address where customer_id = #{id} and del = #{del}")
    List<CustomerReceiveAddress> selectAddressesByDel(@Param("id")int customerId,@Param("del") int del);
}
