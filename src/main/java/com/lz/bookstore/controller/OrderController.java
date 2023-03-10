package com.lz.bookstore.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lz.bookstore.common.Result;
import com.lz.bookstore.controller.dto.OrderDto;
import com.lz.bookstore.entity.Book;
import io.swagger.models.auth.In;
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
 * @since 2023-01-29
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping
    public Result save(@RequestBody Order order) {

        boolean flag = orderService.saveOrUpdate(order);
        return Result.success(flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = orderService.removeById(id);
        return Result.success(flag);
    }

    @GetMapping
    public Result findAll() {
        List<OrderDto> Orders = orderService.findOrder();
        return Result.success(Orders);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        Order order = orderService.getById(id);
        return Result.success(order);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(required = false) Integer customerId,
                           @RequestParam(required = false) Integer bussonId){
        HashMap<String, Object> page = null;
        if(bussonId != null){
            QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("bussion_id",bussonId);
            page = orderService.findPageBubussinId(bussonId,pageNum,pageSize);
        }
        if(customerId !=null ){
            page = orderService.findPageByCustomerId(customerId,pageNum,pageSize);
        }
        if(bussonId == null && customerId ==null)
        page = orderService.findPage(pageNum,pageSize);
        return Result.success(page);
    }

}

