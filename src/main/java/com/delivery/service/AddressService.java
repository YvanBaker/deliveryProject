package com.delivery.service;

import com.delivery.entity.Areas;
import com.delivery.entity.City;
import com.delivery.entity.CustomerAddress;
import com.delivery.entity.Provinces;

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
     */
    CustomerAddress saveCustomerAddress(CustomerAddress customerAddress);

    /**
     * 根据客户Id查询地址
     * @param id
     * @return
     */
    List<CustomerAddress> getAddressByUserId(Integer id);
}
