package com.lz.bookstore.service;

import com.lz.bookstore.common.Result;
import com.lz.bookstore.controller.dto.UserDto;
import com.lz.bookstore.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-12
 */
public interface IUserService extends IService<User> {

    UserDto login(UserDto user);

    User register(UserDto user);
}
