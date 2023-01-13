package com.lz.bookstore.service.impl;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.lz.bookstore.controller.dto.UserDto;
import com.lz.bookstore.entity.User;
import com.lz.bookstore.mapper.UserMapper;
import com.lz.bookstore.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Log log = Log.get();
    @Override
    public Boolean login(UserDto user) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("username", user.getUsername()).eq("password", user.getPassword());

        User one = null;
        try {
            one = this.getOne(query);
            return one != null;
        } catch (Exception e) {
            log.error(e);
            return false;
        }


    }
}
