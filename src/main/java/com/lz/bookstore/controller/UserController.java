package com.lz.bookstore.controller;
import com.lz.bookstore.entity.User;
import com.lz.bookstore.mapper.UserMapper;
import com.lz.bookstore.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    public Integer findCount(){
        return userMapper.findAll();
    }

    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam String username) {
        username = "%" + username +"%";
        Integer total = userMapper.selectTotal(username);
        pageNum = (pageNum - 1) * pageSize;
        List<User> users = userMapper.selectPage(pageNum, pageSize,username);
        Map<String, Object> res = new HashMap<>();
        res.put("data", users);
        res.put("total",total);
        return res;

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
