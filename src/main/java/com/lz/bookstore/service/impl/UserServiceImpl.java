package com.lz.bookstore.service.impl;

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

}
