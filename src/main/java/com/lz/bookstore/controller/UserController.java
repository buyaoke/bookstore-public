package com.lz.bookstore.controller;
import com.lz.bookstore.entity.User;
import com.lz.bookstore.mapper.UserMapper;
import com.lz.bookstore.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> index() {
        return userMapper.findAll();
    }

    @PostMapping()
    public Integer save(@RequestBody User user){
        //新增或更新
       return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id) {
        return userMapper.deleteById(id);
    }
}
