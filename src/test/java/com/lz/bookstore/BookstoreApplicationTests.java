package com.lz.bookstore;

import com.lz.bookstore.controller.dto.OrderDto;
import com.lz.bookstore.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class BookstoreApplicationTests {
    @Autowired
    private IOrderService orderService;

    @Test
    void contextLoads() {
    }

    @Test
    void test(){
        HashMap<String, Object> page = orderService.findPage(1, 2);
    }


}
