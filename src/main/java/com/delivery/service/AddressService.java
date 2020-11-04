package com.delivery.service;

import com.delivery.entity.*;
import com.delivery.exception.customer.AddressNumberException;

import java.util.List;

/**
 * @author Yvan
 * @Description address 服务层
 * @Classname AddressService
 * @Date 2020/10/21 12:54
 */
public interface AddressService {
    /**
     * 查询所有省
     *
     * @return List<Provinces>
     */
    List<Provinces> queryProvincesAll();

    /**
     * 根据 省id 查询 城市
     *
     * @param provinceId 省 id
     * @return List<City>
     */
    List<City> queryCityByProvinceId(String provinceId);

    /**
     * 根据 市id 查询 区
     *
     * @param cityId 市 id
     * @return List<Areas>
     */
    List<Areas> queryAresByCityId(String cityId);

    /**
     * 根据 市id 和 省id 查询 区
     *
     * @param cityId     市 id
     * @param provinceId 省 id
     * @return List<Areas>
     */
    List<Areas> queryAresByCityIdAndProvinceId(String cityId, String provinceId);

    /**
     * 保存用户地址
     *
     * @param customerAddress 用户地址
     * @return CustomerAddress 地址
     * @throws AddressNumberException 有效地址已经有10个了
     */
    CustomerAddress saveCustomerAddress(CustomerAddress customerAddress) throws AddressNumberException;

    /**

     * 用户id
     *
     * @param customerId 用户 id
     * @return List<CustomerAddress>
     */
    List<CustomerAddress> queryCustomerAddresses(int customerId);

    /**
     * 保存 用户送达地址
     *
     * @param address 地址
     * @return CustomerReceiveAddress
     */
    CustomerReceiveAddress saveCustomerReceiveAddress(CustomerReceiveAddress address);

    /**
     * 根据 关联 用户 id 查询 送达地址
     *
     * @param customerId 用户 id
     * @return List<CustomerReceiveAddress>
     */
    List<CustomerReceiveAddress> queryCustomerReceiveAddressesByCustomerId(int customerId);

    /**
     * 更新 关联 用户id 的 送达地址
     *
     * @param address 地址
     * @return true 成功
     */
    boolean renewCustomerReceiveAddresses(CustomerReceiveAddress address);

    /**
     * 根据客户Id查询地址
     * @param id
     * @return
     */
    List<CustomerAddress> getAddressByUserId(Integer id);
}
