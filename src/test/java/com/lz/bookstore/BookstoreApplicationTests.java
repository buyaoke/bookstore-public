package com.lz.bookstore;

import com.lz.bookstore.service.IOrderService;
import com.lz.bookstore.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class BookstoreApplicationTests {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void test(){
        boolean b = orderService.removeById(300);

    }
    @Test
    void test1(){

        userService.removeById(26);
    }



}
