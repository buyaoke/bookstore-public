package com.lz.bookstore.entity;

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
 * @since 2023-01-19
 */
@Getter
@Setter
@TableName("sys_order")
@ApiModel(value = "Order对象", description = "")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "order_id")
    private Integer orderId;
    private Integer bussionId;

    private Integer customerId;

    private Integer bookId;

    private LocalDateTime createTime;

    private LocalDateTime updaeTime;

    private Double orderPrice;


}
