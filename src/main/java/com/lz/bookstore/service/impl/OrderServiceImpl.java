package com.lz.bookstore.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lz.bookstore.controller.dto.OrderDto;
import com.lz.bookstore.entity.Order;
import com.lz.bookstore.mapper.OrderMapper;
import com.lz.bookstore.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-19
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderDto> findOrder() {
        List<OrderDto> order = orderMapper.findOrder();
        return order;

    }
    @Override
    public HashMap<String, Object> findPage(Integer pageNum, Integer pageSize){
        List<OrderDto> order = this.findOrder();
        HashMap<String, Object> hashMap = new HashMap<>();
        Integer size = order.size();
        List<OrderDto> subList = CollUtil.sub(order,(pageNum - 1) * pageSize, pageNum * pageSize);
        hashMap.put("tolal",size);
        hashMap.put("data",subList);
        return hashMap;


    }

    @Override
    public boolean saveOrder(Double orderPrice ,Integer id) {
        return orderMapper.savePrice(orderPrice,id);
    }

    @Override
    public boolean del(Integer id){
        return orderMapper.delPrice(id);
    }
}
