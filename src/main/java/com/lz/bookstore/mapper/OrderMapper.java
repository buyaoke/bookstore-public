package com.lz.bookstore.mapper;

import com.lz.bookstore.controller.dto.OrderDto;
import com.lz.bookstore.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-19
 */
public interface OrderMapper extends BaseMapper<Order> {
    @Select("select sys_book.book_name," +
            "sys_order.order_id," +
            "sys_customer.username as customer_name," +
            "sys_order.create_time," +
            "sys_order.order_price," +
            "sys_bussion.username as bussion_name " +
            "from sys_order " +
            "JOIN sys_book " +
            "on sys_book.book_id = sys_order.book_id " +
            "JOIN sys_customer " +
            "on sys_customer.id = sys_order.customer_id " +
            "join sys_bussion " +
            "on sys_bussion.id = sys_order.bussion_id order by id desc ")
    List<OrderDto> findOrder();

    @Update("update sys_order set order_price = #{orderPrice} where order_id=#{orderId}")
    boolean savePrice(Double orderPrice,Integer orderId);

    @Delete("delete from sys_order where order_id=#{id}")
    boolean delPrice(Integer id);
}
