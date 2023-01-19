package com.lz.bookstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lz.bookstore.controller.dto.OrderDto;
import com.lz.bookstore.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-19
 */
public interface IOrderService extends IService<Order> {

    List<OrderDto> findOrder();

    HashMap<String, Object> findPage(Integer pageNum, Integer pageSize);


    boolean saveOrder(Double orderPrice,Integer orderId);

     boolean del(Integer id);
}
