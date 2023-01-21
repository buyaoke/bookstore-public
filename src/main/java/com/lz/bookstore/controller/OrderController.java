package com.lz.bookstore.controller;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.UpdateById;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lz.bookstore.common.Result;
import com.lz.bookstore.controller.dto.OrderDto;
import com.lz.bookstore.controller.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.lz.bookstore.service.IOrderService;
import com.lz.bookstore.entity.Order;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-19
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/save")
    public Result save(@RequestParam Double orderPrice,@RequestParam Integer orderId) {
        boolean flag = orderService.saveOrder(orderPrice,orderId);
        return Result.success(flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = orderService.del(id);
        return Result.success(flag);
    }

    @GetMapping
    public Result findAll() {
        List<OrderDto> orders = orderService.findOrder();

        return Result.success(orders);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        Order order = orderService.getById(id);
        return Result.success(order);
    }

    @GetMapping("/page")
    public Result findOrderPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {

        HashMap<String, Object> page = orderService.findPage(pageNum,pageSize);
        return Result.success(page);
    }



}
