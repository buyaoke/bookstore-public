package com.lz.bookstore.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lz.bookstore.entity.User;

import com.lz.bookstore.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
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
        return userService.page(userPage,queryWrapper);

    }

    @PostMapping()
    public boolean save(@RequestBody User user){
        //新增或更新
       return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
       return userService.removeById(id);
    }


    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeBatchByIds(ids);
    }

}
