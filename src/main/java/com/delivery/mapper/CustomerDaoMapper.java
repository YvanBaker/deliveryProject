package com.delivery.mapper;

import com.delivery.entity.Customer;
import com.delivery.util.TypeUtil;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Yvan
 * @Description customerDao 的 动态 sql
 * @Classname CustomerDaoMapper
 * @Date 2020/10/23 16:04
 */
public class CustomerDaoMapper {
    /**
     * 插入 sql
     *
     * @param customer 用户
     * @return String sql
     */
    public String insertCustomerSql(Customer customer) {
        return new SQL() {
            {
                INSERT_INTO("customer");
                if (customer.getName() != null &&
                        TypeUtil.isValidString(customer.getName())) {
                    VALUES("name", "#{name}");
                }
                if (customer.getPassword() != null &&
                        TypeUtil.isValidString(customer.getPassword())) {
                    VALUES("password", "#{password}");
                }
                if (customer.getPhone() != null &&
                        TypeUtil.isValidString(customer.getPhone())) {
                    VALUES("phone", "#{phone}");
                }
                if (customer.getSex() != null) {
                    VALUES("sex", "#{sex}");
                }
                if (customer.getCustomerEmail() != null &&
                        TypeUtil.isValidString(customer.getCustomerEmail())) {
                    VALUES("customer_email", "#{customerEmail}");
                }
                if (customer.getCreateTime() != null) {
                    VALUES("create_time", "#{createTime}");
                }
            }
        }.toString();
    }

    /**
     * 更改 用户 sql
     *
     * @param customer 用户
     * @return String sql
     */
    public String updateCustomerSql(Customer customer) {
        return new SQL() {
            {
                UPDATE("customer");
                if (customer.getName() != null &&
                        TypeUtil.isValidString(customer.getName())) {
                    SET("name = #{name}");
                }
                if (customer.getPassword() != null && TypeUtil.isValidString(customer.getPassword())) {
                    SET("password = #{password}");
                }
                if (customer.getPhone() != null && TypeUtil.isValidString(customer.getPhone())) {
                    SET("phone = #{phone}");
                }
                if (customer.getSex() != null) {
                    SET("sex = #{sex}");
                }
                if (customer.getCreateTime() != null) {
                    SET("create_time = #{createTime}");
                }
                if (customer.getCustomerEmail() != null) {
                    SET("customer_email = #{customerEmail}");
                }
            }
        }.toString();
    }
}
