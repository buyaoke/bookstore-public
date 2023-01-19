package com.lz.bookstore.controller.dto;

import com.lz.bookstore.entity.Order;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OrderDto {
   private Integer orderId;
   private String bookName;
   private Date createTime;
   private Double orderPrice;
   private String bussionName;
   private String customerName;


}
