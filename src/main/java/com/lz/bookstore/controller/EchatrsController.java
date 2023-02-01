package com.lz.bookstore.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Month;
import cn.hutool.core.date.Quarter;
import com.lz.bookstore.common.Result;
import com.lz.bookstore.controller.dto.OrderDto;
import com.lz.bookstore.entity.User;
import com.lz.bookstore.service.IOrderService;
import com.lz.bookstore.service.IUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController()
@RequestMapping("/echarts")
public class EchatrsController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    @GetMapping("/revenue")
    public Result get(@RequestParam Integer bussionId){
        Double JANUARY = 0.0;
        Double FEBRUARY = 0.0;
        Double MARCH = 0.0;
        Double APRIL = 0.0;
        Double MAY = 0.0;
        Double JUNE = 0.0;
        Double JULY = 0.0;
        Double AUGUST = 0.0;
        Double SEPTEMBER = 0.0;
        Double OCTOBER = 0.0;
        Double NOVEMBER = 0.0;
        Double DECEMBER = 0.0;
        Double UNDECIMBER = 0.0;
        Double max=0.0;

        List<OrderDto> order = orderService.findOrderByBusionId(bussionId);
        HashSet<String> customerSet = new HashSet<>();
        for (OrderDto orderDto : order) {
            Date createTime = orderDto.getCreateTime();
            Month month = DateUtil.monthEnum(createTime);
            if (max<orderDto.getOrderPrice()){
                max = orderDto.getOrderPrice();
            };
            customerSet.add(orderDto.getCustomerName());
            switch (month){
                case JANUARY:JANUARY+=orderDto.getOrderPrice();break;
                case FEBRUARY:FEBRUARY+=orderDto.getOrderPrice();break;
                case MARCH:MARCH+=orderDto.getOrderPrice();break;
                case APRIL:APRIL+=orderDto.getOrderPrice();break;
                case MAY:MAY+=orderDto.getOrderPrice();break;
                case JUNE:JUNE+=orderDto.getOrderPrice();break;
                case JULY:JULY+=orderDto.getOrderPrice();break;
                case AUGUST:AUGUST+=orderDto.getOrderPrice();break;
                case SEPTEMBER:SEPTEMBER+=orderDto.getOrderPrice();break;
                case OCTOBER:OCTOBER+=orderDto.getOrderPrice();break;
                case NOVEMBER:NOVEMBER+=orderDto.getOrderPrice();break;
                case DECEMBER:DECEMBER+=orderDto.getOrderPrice();break;
                case UNDECIMBER:UNDECIMBER+=orderDto.getOrderPrice();break;
            }
        }
        ArrayList<Double> integers = CollUtil.newArrayList(JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
                JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER, UNDECIMBER);
        int size = customerSet.size();
        Double maxMonth = CollUtil.max(integers);
        HashMap<String, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("max",max);
        objectHashMap.put("maxMonth",maxMonth);
        objectHashMap.put("data",integers);
        objectHashMap.put("customercount",size);
        return Result.success(objectHashMap);

    }


    @GetMapping("/members")
    public Result members(){
        List<User> users = userService.list();
        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;


        for (User user : users) {
            Date createTime = user.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createTime);
            switch (quarter) {
                case Q1:q1++;break;
                case Q2:q2++;break;
                case Q3:q3++;break;
                case Q4:q4++;break;
                default:break;
            }
        }
        return Result.success(CollUtil.newArrayList(q1,q2,q3,q4));
    }

}
