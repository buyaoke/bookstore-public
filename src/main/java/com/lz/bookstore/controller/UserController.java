package com.lz.bookstore.controller;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lz.bookstore.common.Constants;
import com.lz.bookstore.common.Result;
import com.lz.bookstore.controller.dto.UserDto;


import com.lz.bookstore.utils.TokenUtils;
import com.sun.net.httpserver.Authenticator;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.lz.bookstore.service.IUserService;
import com.lz.bookstore.entity.User;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public Result save(@RequestBody User user) {
        boolean flag = userService.saveOrUpdate(user);
        return Result.success(flag);
    }

    @PostMapping("register")
    public Result register(@RequestBody UserDto user) {
        if(StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword()))
            return Result.error(Constants.code_400,"参数错误");
        return Result.success(userService.register(user));
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDto user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password))
            return Result.error(Constants.code_400,"参数错误");
        UserDto userDto = userService.login(user);
        return Result.success(userDto);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {

        boolean flag = userService.removeById(id);
        return Result.success(flag);
    }
    @GetMapping("/username/{username}")
    public Result getone(@PathVariable String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        return Result.success(userService.getOne(userQueryWrapper));
    }

    @GetMapping
    public Result findAll() {
        List<User> users = userService.list();
        return Result.success(users);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        List<User> list = userService.list();
        return Result.success(list);
    }



    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address) {
        IPage<User> userPage = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username)){
            queryWrapper.like("username",username);
        }
        if (!"".equals(email)){
            queryWrapper.like("email",email);
        }
        if (!"".equals(address)){
            queryWrapper.like("address",address);
        }
        queryWrapper.orderByDesc("create_time");

        User currentUser = TokenUtils.getCurrentUser();
        System.out.println("aaaaa"+currentUser);
        IPage<User> page = userService.page(userPage, queryWrapper);
        return Result.success(page);

    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {

        boolean flag = userService.removeBatchByIds(ids);
        return Result.success(flag);
    }

}

