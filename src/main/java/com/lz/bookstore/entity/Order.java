package com.lz.bookstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-29
 */
@Getter
@Setter
  @TableName("sys_order")
@ApiModel(value = "Order对象", description = "")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("订单id")
        @TableId(value = "order_id", type = IdType.AUTO)
      private Integer orderId;

      @ApiModelProperty("商家id")
      private Integer bussionId;

      @ApiModelProperty("客户id")
      private Integer customerId;

      @ApiModelProperty("书籍id")
      private Integer bookId;

      @ApiModelProperty("订单价格")
      private Double orderPrice;

      @ApiModelProperty("创建时间")
      private LocalDateTime createTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime updateTime;

      @ApiModelProperty("是否支付")
      private String isPayed;

      @ApiModelProperty("商品数量")
      private Integer bookCount;


}
