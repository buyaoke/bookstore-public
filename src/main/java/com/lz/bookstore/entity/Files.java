package com.lz.bookstore.entity;

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
 * @since 2023-01-24
 */
@Getter
@Setter
@TableName("sys_file")
@ApiModel(value = "File对象", description = "")
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
      @TableId()
      private Integer id;

      @ApiModelProperty("文件名称")
      private String name;

      @ApiModelProperty("文件类型")
      private String type;

      @ApiModelProperty("文件大小")
      private Double size;

      @ApiModelProperty("下载链接")
      private String url;

      @ApiModelProperty("是否删除")
      private Boolean isDelete;

      @ApiModelProperty("是否禁链接")
      private Integer enable;


}
