package com.lz.bookstore.service;

import com.lz.bookstore.controller.dto.OrderDto;
import com.lz.bookstore.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-29
 */
public interface IOrderService extends IService<Order> {

    List<OrderDto> findOrder();

    List<OrderDto> findOrderByBusionId(Integer bussionId);

    HashMap<String, Object> findPage(Integer pageNum, Integer pageSize);

    HashMap<String, Object> findPageByCustomerId(Integer customerId,Integer pageNum, Integer pageSize);

    HashMap<String, Object> findPageBubussinId(Integer customerId,Integer pageNum, Integer pageSize);
}
