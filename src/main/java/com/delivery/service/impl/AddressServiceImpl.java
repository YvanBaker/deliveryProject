package com.delivery.service.impl;

import com.delivery.dao.*;
import com.delivery.entity.*;
import com.delivery.exception.customer.AddressNumberException;
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

    @Resource
    private CustomerReceiveAddressDao customerReceiveAddressDao;

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
    public CustomerAddress saveCustomerAddress(CustomerAddress customerAddress) throws AddressNumberException {
        List<CustomerAddress> resDataList =
                customerAddressDao.selectCustomerAddressByUserIdAndDel(customerAddress.getUserId(), 0);
        if (resDataList.size() > 9) {
            throw new AddressNumberException("已经存在10个有效地址!!");
        }
        if (resDataList.isEmpty()) {
            customerAddressDao.insertCustomerAddress(customerAddress);
            return customerAddress;
        }
        for (CustomerAddress item : resDataList) {
            if (item.equals(customerAddress)) {
                return item;
            }
        }
        customerAddressDao.insertCustomerAddress(customerAddress);
        return customerAddress;
    }

    @Override

    public List<CustomerAddress> queryCustomerAddresses(int customerId) {
        return customerAddressDao.selectCustomerAddressByIdUserId(customerId);
    }

    @Override
    public List<CustomerAddress> queryEffectiveCustomerAddress(int customerId) {
        return customerAddressDao.selectCustomerAddressByUserIdAndDel(customerId, 0);
    }

    @Override
    public CustomerReceiveAddress saveCustomerReceiveAddress(CustomerReceiveAddress address) {
        List<CustomerReceiveAddress> resAddressList =
                customerReceiveAddressDao.selectAddressByCustomerId(address.getCustomerId());
        if (resAddressList.isEmpty()) {
            customerReceiveAddressDao.insertAddress(address);
            return address;
        }
        for (CustomerReceiveAddress item : resAddressList) {
            if (item.equals(address)) {
                return item;
            }
        }
        customerReceiveAddressDao.insertAddress(address);
        return address;
    }

    @Override
    public List<CustomerReceiveAddress> queryCustomerReceiveAddressesByCustomerId(int customerId) {
        return customerReceiveAddressDao.selectAddressByCustomerId(customerId);
    }

    @Override
    public boolean renewCustomerReceiveAddresses(CustomerReceiveAddress address) {
        return customerReceiveAddressDao.updateAddress(address) > 0;
    }

    @Override
    public List<CustomerAddress> getAddressByUserId(Integer id) {
        return customerAddressDao.selectCustomerAddressByIdUserId(id);
    }
}
