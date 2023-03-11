package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import org.example.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * sdg拉偏表实体类
 *
 * @author AI
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Sdg对象", description = "sdg拉偏表")
@TableName("sdg")
public class Sdg extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId(value = "sdg_id", type = IdType.AUTO)
  private Integer sdgId;
    /**
     * 变量名称
     */
  @ApiModelProperty(value = "变量名称")
  private String variableName;
    /**
     * 拉偏方向
     */
  @ApiModelProperty(value = "拉偏方向")
  @TableField("pullDirection")
  private String pullDirection;
    /**
     * 单元id
     */
  @ApiModelProperty(value = "单元id")
  private Integer unitId;
    /**
     * 模型id
     */
  @ApiModelProperty(value = "模型id")
  private Integer modelId;
    /**
     * 项目id
     */
  @ApiModelProperty(value = "项目id")
  private Integer projectId;


}
