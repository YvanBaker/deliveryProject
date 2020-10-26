package com.delivery.service;

import com.delivery.entity.Customer;
import com.delivery.exception.customer.CustomerAttributesNullException;
import com.delivery.exception.customer.CustomerNameRepeatException;
import com.delivery.exception.customer.CustomerNullException;

import java.util.List;

/**
 * @author Yvan
 * @Description 用户的服务层
 * @Classname CustomerService
 * @Date 2020/10/26 9:52
 */
public interface CustomerService {
    /**
     * 保存用户
     *
     * @param customer 用户
     * @return Customer
     * @throws CustomerNameRepeatException     用户名重复异常
     * @throws CustomerAttributesNullException 必要参数为空
     */
    Customer saveCustomer(Customer customer) throws CustomerNameRepeatException, CustomerAttributesNullException;


    /**
     * 登录用户逻辑
     *
     * @param name     账户 或 邮箱
     * @param password 密码
     * @return Customer
     * @throws CustomerAttributesNullException 必要参数不为空或不合法
     * @throws CustomerNullException           对象为空（查不到用户）
     */
    Customer loginCustomer(String name, String password) throws CustomerAttributesNullException, CustomerNullException;

    /**
     * 根据 id 查询 用户
     *
     * @param customer 用户
     * @return Customer
     */
    Customer queryCustomerById(Customer customer);

    /**
     * 根据 id 查询 用户
     *
     * @param id id
     * @return Customer
     */
    Customer queryCustomerById(Integer id);

    /**
     * 根据 email 查询 用户
     *
     * @param customer 用户
     * @return Customer
     */
    Customer queryCustomerByEmail(Customer customer);

    /**
     * 根据 email 查询 用户
     *
     * @param email 邮箱
     * @return Customer
     */
    Customer queryCustomerByEmail(String email);

    /**
     * 根据 phone 查询 用户
     *
     * @param customer 用户
     * @return Customer
     */
    Customer queryCustomerByPhone(Customer customer);

    /**
     * 根据 phone 查询 用户
     *
     * @param phone 电话
     * @return Customer
     */
    Customer queryCustomerByPhone(String phone);

    /**
     * 根据 sex 查询 用户
     *
     * @param customer 用户
     * @return List<Customer>
     */
    List<Customer> queryCustomerBySex(Customer customer);

    /**
     * 根据 sex 查询 用户
     *
     * @param sex 性别
     * @return List<Customer>
     */
    List<Customer> queryCustomerBySex(Integer sex);

    /**
     * 根据 账号 和 密码 查询 用户
     *
     * @param customer 用户
     * @return Customer
     */
    Customer queryCustomerByNameAndPassword(Customer customer);

    /**
     * 根据 账号 和 密码 查询 用户
     *
     * @param name     名字
     * @param password 密码
     * @return Customer
     */
    Customer queryCustomerByNameAndPassword(String name, String password);
}