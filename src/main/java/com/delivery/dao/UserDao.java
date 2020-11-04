package com.delivery.dao;

import com.delivery.entity.User;
import com.delivery.mapper.UserDaoMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yvan
 * @Description 用户dao层
 * @Classname UserDao
 * @Date 2020/10/16 17:55
 */
@Repository
public interface UserDao {

    /**
     * 插入用户
     *
     * @param user 用户
     * @return 条数
     */
    @Insert("insert into user(user_number, user_name, user_password, user_salary, user_date, user_gender, user_company, user_phone, user_remark, email) VALUE " +
            "(#{userName},#{userName},#{userPassword},#{userSalary},#{userDate},#{userGender},#{userCompany},#{userPhone},#{userRemark},#{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertUser(User user);


    /**
     * 更新用户信息
     *
     * @param user 用户
     * @return 条数
     */
    @UpdateProvider(type = UserDaoMapper.class, method = "updateUserSql")
    int updateUser(User user);

    @SelectProvider(type = UserDaoMapper.class, method = "selectUserBySql")
    List<User> selectUserBy(User user);


    /**
     * 根据用户id查询
     *
     * @param id 用户id
     * @return user
     */
    @Select("select id, user_number, user_name, user_password, user_salary, user_date, user_gender, user_company, user_phone, user_remark, email " +
            "from user where id = #{id}")
    User selectUserById(@Param("id") int id);

    /**
     * 根据用户name 查询
     *
     * @param name 用户 name
     * @return user
     */
    @Select("select id, user_number, user_name, user_password, user_salary, user_date, user_gender, user_company, user_phone, user_remark, email " +
            "from user where user_name = #{name}")
    User selectUserByName(@Param("name") String name);

    /**
     * 根据用户 email 查询
     *
     * @param email 用户 email
     * @return user
     */
    @Select("select id, user_number, user_name, user_password, user_salary, user_date, user_gender, user_company, user_phone, user_remark, email " +
            "from user where email = #{email}")
    User selectUserByEmail(@Param("email") String email);

    /**
     * 查询所有用户
     *
     * @return list
     */
    @Select("select id, user_number, user_name, user_password, user_salary, user_date, user_gender, user_company, user_phone, user_remark, email from user")
    List<User> selectUserAll();
}
