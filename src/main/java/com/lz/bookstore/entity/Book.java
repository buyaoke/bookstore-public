package com.lz.bookstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2023-01-18
 */
@Getter
@Setter
@TableName("sys_book")
@ApiModel(value = "Book对象", description = "")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("书籍id")
      @TableId(value = "book_id", type = IdType.AUTO)
      private Integer bookId;

      @ApiModelProperty("书籍价格")
      private Double bookPrice;

      @ApiModelProperty("书籍名称")
      private String bookName;

      @ApiModelProperty("书籍描述")
      private String bookDescrip;

      @ApiModelProperty("书籍作者")
      private String bookAuthor;

      @ApiModelProperty("书籍类型")
      private String bookType;

      @ApiModelProperty("书籍图片")
      private String bookImg;




}
