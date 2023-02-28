package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.example.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 单元表实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Unit对象", description = "单元表")
@TableName("unit")
public class Unit extends BaseEntity {

    private static final long serialVersionUID = 1L;

  private Integer unitId;
    /**
     * 单元名称
     */
  @ApiModelProperty(value = "单元名称")
  private String unitName;
    /**
     * 创建者
     */
  @ApiModelProperty(value = "创建者")
  private String creater;
    /**
     * 装置
     */
  @ApiModelProperty(value = "装置")
  private String installation;
    /**
     * 项目名称
     */
  @ApiModelProperty(value = "项目名称")
  private Integer projectId;


}
