package com.lz.bookstore.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.lz.bookstore.common.Constants;
import com.lz.bookstore.common.Result;
import com.lz.bookstore.controller.dto.UserDto;
import com.lz.bookstore.entity.User;
import com.lz.bookstore.exception.ServiceException;
import com.lz.bookstore.mapper.UserMapper;
import com.lz.bookstore.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.bookstore.utils.TokenUtils;
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
    public UserDto login(UserDto user) {
        User one = this.getUserInfo(user);

        if(one != null){
            BeanUtil.copyProperties(one,user,true);
            String token = TokenUtils.getToken(one.getId().toString(), one.getPassword());
            user.setToken(token);
            return user;
        } else {
            throw new ServiceException(Constants.code_600,"用户名或者密码错误");
        }





    }

    @Override
    public User register(UserDto user) {
        User one = getUserInfo(user);
        if (one == null){
            one = new User();
            BeanUtil.copyProperties(user,one,true);
            save(one);
        } else {
            throw new ServiceException(Constants.code_600,"用户名已经存在");
        }
        return one;
    }


    private User getUserInfo(UserDto user){
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("username", user.getUsername()).eq("password", user.getPassword());

        User one = null;
        try {
            one = this.getOne(query);
        }catch (ServiceException e){
            log.error(e);
            throw new ServiceException(Constants.code_500,"系统错误");
        }
        return one;

    }
}
