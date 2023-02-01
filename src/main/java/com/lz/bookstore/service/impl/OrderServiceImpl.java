package com.lz.bookstore.service.impl;

import cn.hutool.core.collection.CollUtil;
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
 * @since 2023-01-29
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
    public List<OrderDto> findOrderByBusionId(Integer bussionId) {
        return orderMapper.findOrderBybussionId(bussionId);
    }

    @Override
    public HashMap<String, Object> findPage(Integer pageNum, Integer pageSize){
        List<OrderDto> order = this.findOrder();
        HashMap<String, Object> hashMap = this.page(order, pageNum, pageSize);
        return hashMap;


    }

    @Override
    public HashMap<String, Object> findPageByCustomerId(Integer customerId,Integer pageNum, Integer pageSize) {
        List<OrderDto> order = orderMapper.findOrderByCutomerId(customerId);
        HashMap<String, Object> page = this.page(order, pageNum, pageSize);
        return page;
    }

    @Override
    public HashMap<String, Object> findPageBubussinId(Integer bussionId,Integer pageNum, Integer pageSize) {
        List<OrderDto> order = orderMapper.findOrderBybussionId(bussionId);
        HashMap<String, Object> page = this.page(order, pageNum, pageSize);
        return page;
    }

    /**
     *
     * @param list 通过mapper查询后的所有数据list集合
     * @param pageNum 第几页
     * @param pageSize 每页展示的数据量
     * @return
     */
    private HashMap<String,Object> page(List<OrderDto> list,Integer pageNum, Integer pageSize){
        HashMap<String, Object> page = new HashMap<>();
        Integer size = list.size();
        List<OrderDto> subList = CollUtil.sub(list,(pageNum - 1) * pageSize, pageNum * pageSize);
        page.put("total",size);
        page.put("data",subList);
        return page;
    }

}
