package com.delivery.mapper;

import com.delivery.entity.User;
import com.delivery.util.TypeUtil;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Yvan
 * @Description UserDao动态sql映射
 * @Classname UserDaoMapper
 * @Date 2020/10/16 18:19
 */
public class UserDaoMapper {

    /**
     * insert 的 动态 sql
     *
     * @param user user
     * @return sql
     */
    public String insertUserSql(User user) {
        return new SQL() {
            {
                INSERT_INTO("user");
                if (TypeUtil.isValidString(user.getUserNumber())) {
                    VALUES("user_number", "#{userNumber}");
                }
                if (TypeUtil.isValidString(user.getUserName())) {
                    VALUES("user_name", "#{userName}");
                }
                if (TypeUtil.isValidString(user.getUserPassword())) {
                    VALUES("user_password", "#{userPassword}");
                }
                if (user.getUserSalary() != null) {
                    VALUES("user_salary", "#{userSalary}");
                }
                if (user.getUserDate() != null) {
                    VALUES("user_date", "#{userDate}");
                }
                if (TypeUtil.isValidString(user.getUserGender())) {
                    VALUES("user_gender", "#{userGender}");
                }
                if (TypeUtil.isValidString(user.getUserCompany())) {
                    VALUES("user_company", "#{userCompany}");
                }
                if (TypeUtil.isValidString(user.getUserRemark())) {
                    VALUES("user_remark", "#{userRemark}");
                }
                if (TypeUtil.isValidString(user.getEmail())) {
                    VALUES("email", "#{email}");
                }
            }
        }.toString();
    }


    /**
     * 更新的动态sql
     *
     * @param user 用户
     * @return sql
     */
    public String updateUserSql(User user) {
        return new SQL() {
            {
                UPDATE("user");
                if (user.getUserPassword() != null && !"".equals(user.getUserPassword())) {
                    SET("user_password = #{userPassword}");
                }
                if (user.getUserSalary() != null && user.getUserSalary() >= 0) {
                    SET("user_salary = #{userSalary}");
                }
                if (user.getUserDate() != null) {
                    SET("user_date = #{userDate}");
                }
                if (user.getUserGender() != null && !"".equals(user.getUserGender())) {
                    SET("user_gender = #{userGender}");
                }
                if (user.getUserCompany() != null && !"".equals(user.getUserCompany())) {
                    SET("user_company = #{userCompany}");
                }
                if (user.getUserPhone() != null && !"".equals(user.getUserPhone())) {
                    SET("user_phone = #{userPhone} ");
                }
                if (user.getUserRemark() != null && !"".equals(user.getUserRemark())) {
                    SET("user_remark = #{userRemark}");
                }
                if (user.getEmail() != null && !"".equals(user.getEmail())) {
                    SET("email = #{email}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }

    /**
     * 查询语句
     *
     * @param user user
     * @return sql
     */
    public String selectUserBySql(User user) {
        return new SQL() {
            {
                SELECT("*");
                FROM("user");
                if (user.getUserName() != null) {
                    WHERE("user_name = #{userName}");
                }
                OR();
                if (user.getEmail() != null) {
                    WHERE("email = #{email}");
                }
            }
        }.toString();
    }
}
