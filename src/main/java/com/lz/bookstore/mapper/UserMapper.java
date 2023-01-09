package com.lz.bookstore.mapper;

import com.lz.bookstore.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from sys_user")
    List<User> findAll();

    @Insert("insert into sys_user(username,password,nickname,email,phone,address) value(#{username},#{password},"
            + "#{nickname},#{email},#{phone},#{address});")
    Integer insert(User user);



    int update(User user);


    @Delete("delete  from sys_user where id = #{id}")
    Integer deleteById(@Param("id") Integer id);
}
