package com.lz.bookstore.mapper;

import com.lz.bookstore.controller.dto.OrderDto;
import com.lz.bookstore.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-29
 */
public interface OrderMapper extends BaseMapper<Order> {
    @Select("select sys_book.book_name," +
            "sys_order.order_id, sys_order.book_count," +
            "sys_user.nickname as customer_name," +
            "sys_order.create_time," +
            "sys_order.order_price," +
            "b.username as bussion_name " +
            "from sys_order " +
            "left JOIN sys_book " +
            "on sys_book.book_id = sys_order.book_id " +
            "left JOIN sys_user " +
            "on sys_user.id = sys_order.customer_id " +
            "left join sys_user b " +
            "on b.id = sys_order.bussion_id order by order_id desc ")
    List<OrderDto> findOrder();
}
