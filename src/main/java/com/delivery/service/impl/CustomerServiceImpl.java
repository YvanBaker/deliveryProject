package com.delivery.service.impl;

import com.delivery.dao.CustomerAddressDao;
import com.delivery.dao.CustomerDao;
import com.delivery.dao.CustomerReceiveAddressDao;
import com.delivery.dao.CustomerWorkOrderDao;
import com.delivery.entity.Customer;
import com.delivery.entity.CustomerWorkOrder;
import com.delivery.exception.customer.CustomerAttributesNullException;
import com.delivery.exception.customer.CustomerNameRepeatException;
import com.delivery.exception.customer.CustomerNullException;
import com.delivery.model.PickupAddress;
import com.delivery.service.CustomerService;
import com.delivery.util.TypeUtil;
import com.delivery.util.UuidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Yvan
 * @Description
 * @Classname CustomerServiceImpl
 * @Date 2020/10/26 10:11
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerDao customerDao;

    @Resource
    private CustomerWorkOrderDao customerWorkOrderDao;

    @Resource
    private CustomerAddressDao customerAddressDao;

    @Resource
    private CustomerReceiveAddressDao customerReceiveAddressDao;

    @Override
    public Customer saveCustomer(Customer customer) throws CustomerNameRepeatException, CustomerAttributesNullException {
        if (customer.getName() == null || customer.getPassword() == null || customer.getCustomerEmail() == null) {
            throw new CustomerAttributesNullException("必要参数为空");
        }
        if (customerDao.selectCustomerByName(customer.getName()) != null) {
            throw new CustomerNameRepeatException("名字重复");
        }
        customerDao.insertCustomer(customer);
        return customer;
    }

    @Override
    public boolean resetPassword(String email, String newPassword) {
        int i = customerDao.updatePasswordByEmail(email, newPassword);
        return i > 0;
    }

    @Override
    public Customer loginCustomer(String name, String password) throws CustomerAttributesNullException, CustomerNullException {
        if (!TypeUtil.isValidString(name) ||
                !TypeUtil.isValidString(password)) {
            throw new CustomerAttributesNullException("必要参数为空");
        }
        Customer resData = customerDao.selectCustomerByNameAndPassword(name, password);
        if (resData == null) {
            customerDao.selectCustomerByEmailAndPassword(name, password);
        }
        if (resData == null) {
            throw new CustomerNullException("账户或密码错误，查询不到该用户！！");
        }
        return resData;
    }

    @Override
    public Customer queryCustomerById(Customer customer) {
        Customer resData = customerDao.selectCustomerById(customer.getId());
        return resData;
    }

    @Override
    public Customer queryCustomerById(Integer id) {
        Customer reData = customerDao.selectCustomerById(id);
        return reData;
    }

    @Override
    public Customer queryCustomerByEmail(Customer customer) {
        Customer resData = customerDao.selectCustomerByEmail(customer.getCustomerEmail());
        return resData;
    }

    @Override
    public Customer queryCustomerByEmail(String email) {
        Customer resData = customerDao.selectCustomerByEmail(email);
        return resData;
    }

    @Override
    public Customer queryCustomerByPhone(Customer customer) {
        Customer resData = customerDao.selectCustomerByPhone(customer.getPhone());
        return resData;
    }

    @Override
    public Customer queryCustomerByPhone(String phone) {
        Customer resData = customerDao.selectCustomerByPhone(phone);
        return resData;
    }

    @Override
    public List<Customer> queryCustomerBySex(Customer customer) {
        List<Customer> resList = customerDao.selectCustomerBySex(customer.getSex());
        return resList;
    }

    @Override
    public List<Customer> queryCustomerBySex(Integer sex) {
        List<Customer> resList = customerDao.selectCustomerBySex(sex);
        return resList;
    }

    @Override
    public Customer queryCustomerByNameAndPassword(Customer customer) {
        Customer resData = customerDao.selectCustomerByNameAndPassword(customer.getName(), customer.getPassword());
        return resData;
    }

    @Override
    public List<PickupAddress> queryPickupsByUserId(int userId) {
        List<PickupAddress> resData = customerAddressDao.selectEffectivePickAddressByUserId(userId);
        return resData;
    }

    @Override
    public List<PickupAddress> queryReceiveByUserId(int userId) {
        List<PickupAddress> resData = customerAddressDao.selectEffectiveReceiveAddressByUserId(userId);
        return resData;
    }

    @Override
    public Customer queryCustomerByNameAndPassword(String name, String password) {
        Customer resData = customerDao.selectCustomerByNameAndPassword(name, password);
        return resData;
    }

    @Override
    public Customer queryCustomerByName(String name) {
        Customer resData = customerDao.selectCustomerByName(name);
        return resData;
    }

    @Override
    public List<Customer> fuzzyQueryCustomerByName(String name) {
        List<Customer> resData = customerDao.likeCustomerByName(name);
        return resData;
    }

    @Override
    public CustomerWorkOrder saveOrder(CustomerWorkOrder customerWorkOrder) {
        customerWorkOrder.setWorkOrderUuid(UuidUtil.getUuid());
        customerWorkOrder.setCreateTime(new Date(System.currentTimeMillis()));
        customerWorkOrderDao.insertOrder(customerWorkOrder);
        return customerWorkOrder;
    }

    @Override
    public Customer findCustomerByTelephone(String clientPhone) {
        return customerDao.selectCustomerByPhone(clientPhone);
    }
}
