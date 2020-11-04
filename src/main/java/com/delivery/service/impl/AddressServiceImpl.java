package com.delivery.service.impl;

import com.delivery.dao.AreasDao;
import com.delivery.dao.CityDao;
import com.delivery.dao.CustomerAddressDao;
import com.delivery.dao.ProvincesDao;
import com.delivery.entity.Areas;
import com.delivery.entity.City;
import com.delivery.entity.CustomerAddress;
import com.delivery.entity.Provinces;
import com.delivery.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yvan
 * @Description
 * @Classname AddressServiceImpl
 * @Date 2020/10/21 13:08
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Resource
    private AreasDao areasDao;

    @Resource
    private CityDao cityDao;

    @Resource
    private ProvincesDao provincesDao;

    @Resource
    private CustomerAddressDao customerAddressDao;

    @Override
    public List<Provinces> queryProvincesAll() {
        return provincesDao.selectAll();
    }

    @Override
    public List<City> queryCityByProvinceId(String provinceId) {
        return cityDao.selectAreaByProvinceId(provinceId);
    }

    @Override
    public List<Areas> queryAresByCityId(String cityId) {
        return areasDao.selectAreaByCityId(cityId);
    }

    @Override
    public List<Areas> queryAresByCityIdAndProvinceId(String cityId, String provinceId) {
        return areasDao.selectAreaByCityIdAndProvinceId(cityId, provinceId);
    }

    @Override
    public CustomerAddress saveCustomerAddress(CustomerAddress customerAddress) {
        List<CustomerAddress> customerAddressList = customerAddressDao.selectCustomerAddressByIdUserId(customerAddress.getUserId());
        if (customerAddressList.isEmpty()) {
            customerAddressDao.insertCustomerAddress(customerAddress);
            return customerAddress;
        }
        for (CustomerAddress item : customerAddressList) {
            if (item.equals(customerAddress)) {
                return item;
            }
        }
        customerAddressDao.insertCustomerAddress(customerAddress);
        return customerAddress;
    }

    @Override
    public List<CustomerAddress> getAddressByUserId(Integer id) {
        return customerAddressDao.selectCustomerAddressByIdUserId(id);
    }
}
