package com.lz.bookstore.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.bookstore.entity.User;
import com.lz.bookstore.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {

    public boolean saveUser(User user){
       return this.saveOrUpdate(user);

    }

//    public int save(User user) {
//        if(user.getId() == null){
//            return userMapper.insert(user);
//        } else {
//            return userMapper.update(user);
//        }
//    }


}
