package com.delivery.utilentity;

import com.delivery.entity.Customer;
import com.delivery.entity.CustomerAddress;

import java.util.List;

/**
 * @author wenJ
 * @Description
 * @Classname CustomerAndAddress
 * @Date 2020/11/1 16:24
 */
public class CustomerAndAddress {
    private Customer customer;
    private List<CustomerAddress> customerAddress;

    public CustomerAndAddress() {
    }

    public CustomerAndAddress(Customer customer, List<CustomerAddress> customerAddress) {
        this.customer = customer;
        this.customerAddress = customerAddress;
    }

    @Override
    public String toString() {
        return "CustomerAndAddress{" +
                "customer=" + customer +
                ", customerAddress=" + customerAddress +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CustomerAddress> getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(List<CustomerAddress> customerAddress) {
        this.customerAddress = customerAddress;
    }
}
