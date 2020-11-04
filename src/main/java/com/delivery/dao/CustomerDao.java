package com.delivery.dao;

import com.delivery.entity.Customer;
import com.delivery.mapper.CustomerDaoMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yvan
 * @Description customer dao 层
 * @Classname CustomerDao
 * @Date 2020/10/23 15:57
 */
@Repository
public interface CustomerDao {
    /**
     * 插入用户
     *
     * @param customer 用户
     * @return int
     */
    @InsertProvider(type = CustomerDaoMapper.class, method = "insertCustomerSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertCustomer(Customer customer);

    /**
     * 更新 用户 信息
     *
     * @param customer 用户
     * @return int
     */
    @UpdateProvider(type = CustomerDaoMapper.class, method = "updateCustomerSql")
    int updateCustomer(Customer customer);

    /**
     * 根据 id 查询
     *
     * @param id id
     * @return Customer 用户
     */
    @Select("select id, phone, password, name, customer_email, create_time, sex from customer where id = #{id}")
    Customer selectCustomerById(@Param("id") int id);

    /**
     * 根据 姓名 查询
     *
     * @param name 姓名
     * @return Customer
     */
    @Select("select id, phone, password, name, customer_email, create_time, sex from customer where name = #{name}")
    Customer selectCustomerByName(@Param("name") String name);

    /**
     * 根据 邮箱 查询
     *
     * @param email 邮箱
     * @return Customer
     */
    @Select("select id, phone, password, name, customer_email, create_time, sex from customer where customer_email = #{email}")
    Customer selectCustomerByEmail(@Param("email") String email);

    /**
     * 根据 电话 查询
     *
     * @param phone 电话
     * @return Customer
     */
    @Select("select id, phone, password, name, customer_email, create_time, sex from customer where phone = #{phone}")
    Customer selectCustomerByPhone(@Param("phone") String phone);

    /**
     * 根据 名字 和 密码 查询
     *
     * @param name     名字
     * @param password 密码
     * @return Customer 用户
     */
    @Select("select id, phone, password, name, customer_email, create_time, sex from customer where name = #{name} and password = #{password}")
    Customer selectCustomerByNameAndPassword(@Param("name") String name, @Param("password") String password);

    /**
     * 根据 邮箱 和 密码 查询 用户
     *
     * @param email    邮箱
     * @param password 密码
     * @return Customer 用户
     */
    @Select("select * from customer where customer_email = #{email} and password = #{password}")
    Customer selectCustomerByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    /**
     * 根据 性别 查询
     *
     * @param sex 性别
     * @return List<CustomerService>
     */
    @Select("select id, phone, password, name, customer_email, create_time, sex from customer where sex = #{sex}")
    List<Customer> selectCustomerBySex(@Param("sex") int sex);

    /**
     * 根据 电话
     * @param phone
     * @return
     */
    @Select("select id, phone, password, name, customer_email, create_time, sex  from customer where phone=#{phone}")
    Customer getCustomerByTelephone(@Param("phone") String phone);

    @Select("select id, phone, password, name, customer_email, create_time, sex, decidedzone_id  from customer where decidedzone_id is null")
    List<Customer> selectNoAssociationsCustomer();

}
