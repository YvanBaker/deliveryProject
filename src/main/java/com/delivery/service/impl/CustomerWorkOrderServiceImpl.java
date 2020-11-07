package com.delivery.service.impl;

import com.delivery.dao.CustomerAddressDao;
import com.delivery.dao.CustomerReceiveAddressDao;
import com.delivery.dao.CustomerWorkOrderDao;
import com.delivery.entity.CustomerAddress;
import com.delivery.entity.CustomerReceiveAddress;
import com.delivery.entity.CustomerWorkOrder;
import com.delivery.model.CustomerWorkOrderInfo;
import com.delivery.service.CustomerWorkOrderService;
import com.delivery.util.UuidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yvan
 * @Description
 * @Classname CustomerWorkOrderServiceImpl
 * @Date 2020/11/5 21:24
 */
@Service("customerWorkOrderService")
public class CustomerWorkOrderServiceImpl implements CustomerWorkOrderService {

    @Resource
    private CustomerWorkOrderDao customerWorkOrderDao;

    @Resource
    private CustomerAddressDao customerAddressDao;

    @Resource
    private CustomerReceiveAddressDao customerReceiveAddressDao;

    @Override
    public CustomerWorkOrder generateOrder(CustomerWorkOrder customerWorkOrder) {
        customerWorkOrder.setWorkOrderUuid(UuidUtil.getUuid());
        customerWorkOrder.setCreateTime(new Date(System.currentTimeMillis()));
        customerWorkOrder.setConsummation(0);
        customerWorkOrderDao.insertOrder(customerWorkOrder);
        return customerWorkOrder;
    }

    @Override
    public List<CustomerWorkOrderInfo> queryOrder(int customerId) {
        List<CustomerWorkOrderInfo> resList = new ArrayList<>();
        List<CustomerWorkOrder> selectDatsList =
                customerWorkOrderDao.selectOrderByCustomerId(customerId);
        for (CustomerWorkOrder customerWorkOrder : selectDatsList) {
            CustomerAddress customerAddress =
                    customerAddressDao.selectCustomerAddressById(customerWorkOrder.getPickupAddressId());
            CustomerReceiveAddress receiveAddress =
                    customerReceiveAddressDao.selectAddressesById(customerWorkOrder.getReceiveAddressId());
            resList.add(new CustomerWorkOrderInfo(customerWorkOrder, customerAddress, receiveAddress));
        }
        return resList;
    }

    @Override
    public boolean delOrder(int id) {
        int i = customerWorkOrderDao.deleteOrder(id);
        return i > 0;
    }

    @Override
    public int deleteOrder(int id) {
        customerWorkOrderDao.deleteOrder(id);
        return 0;
    }

    @Override
    public boolean renewOrder(CustomerWorkOrder order) {
        int i = customerWorkOrderDao.updateOrder(order);
        return i > 0;
    }
}
