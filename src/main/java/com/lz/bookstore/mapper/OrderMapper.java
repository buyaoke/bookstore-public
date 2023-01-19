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

    @Select("select sys_book.book_name,\n" +
            "\t\t\t\tsys_order.order_id,\n" +
            "\t\t\t\tsys_customer.username as customer_name,\n" +
            "\t\t\t\tsys_order.create_time,\n" +
            "\t\t\t\tsys_order.order_price,\n" +
            "\t\t\t\tsys_bussion.username as bussion_name \n" +
            "\t\t\t\t\n" +
            "\t\t\t\tfrom sys_order \n" +
            "\tJOIN sys_book \n" +
            "\t\ton sys_book.book_id = sys_order.book_id\n" +
            "\tJOIN sys_customer\n" +
            "\t\ton sys_customer.id = sys_order.customer_id\n" +
            "\tjoin sys_bussion\n" +
            "\t\ton sys_bussion.id = sys_order.bussion_id\n")
    List<OrderDto> findOrder();

    @Update("update sys_order set order_price = #{orderPrice} where order_id=#{orderId}")
    boolean savePrice(Double orderPrice,Integer orderId);
    @Delete("delete from sys_order where order_id=#{id}")
    boolean delPrice(Integer id);
}
