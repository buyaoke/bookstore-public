package com.lz.bookstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
 * @since 2023-01-27
 */
@Getter
@Setter
@TableName("sys_comment")
@ApiModel(value = "Comment对象", description = "")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("内容")
      private String content;

      @ApiModelProperty("评论人ID")
      private Integer userId;

      @ApiModelProperty("评论时间")
      private Date time;

      @ApiModelProperty("最上级的父级ID")
      private Integer pid;

      @ApiModelProperty("关联文章的ID")
      private Integer orginId;

      @ApiModelProperty("关联书籍的ID")
      private Integer bookId;

      @TableField(exist = false)
      private String nickname;

      @TableField(exist = false)
      private String avatarUrl;

      @TableField(exist = false)
      private List<Comment> children;

      @TableField(exist = false)
      private String pNickname;

      @TableField(exist = false)
      private Integer pUserId;




}
