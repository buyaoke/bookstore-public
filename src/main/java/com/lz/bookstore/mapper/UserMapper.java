package com.lz.bookstore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lz.bookstore.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
//    @Select("select count(*) t from sys_user ")
//    Integer findAll();
//
//    @Insert("insert into sys_user(username,password,nickname,email,phone,address) value(#{username},#{password},"
//            + "#{nickname},#{email},#{phone},#{address});")
//    Integer insert(User user);
//
//
//
//    int update(User user);
//
//
//    @Delete("delete  from sys_user where id = #{id}")
//    Integer deleteById(@Param("id") Integer id);
//
//    @Select("select * from sys_user where username like #{username} limit #{pageNum},#{pageSize}")
//    List<User> selectPage(Integer pageNum,Integer pageSize,String username);
//
//    @Select("select count(*) from sys_user where username like #{username}")
//    Integer selectTotal(String userName);
}
