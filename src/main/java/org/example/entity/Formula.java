package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import org.example.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 变量公式表实体类
 *
 * @author AI
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Formula对象", description = "变量公式表")
@TableName("formula")
public class Formula extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 变量公式id
     */
  @ApiModelProperty(value = "变量公式id")
  @TableId(value = "formula_id", type = IdType.AUTO)
  private Integer formulaId;
    /**
     * 变量公式右边
     */
  @ApiModelProperty(value = "变量公式右边")
  private String formulaRight;
    /**
     * 变量公式左边
     */
  @ApiModelProperty(value = "变量公式左边")
  private String formulaLeft;
    /**
     * 项目id
     */
  @ApiModelProperty(value = "项目id")
  private Integer projectId;
    /**
     * 单元id
     */
  @ApiModelProperty(value = "单元id")
  private Integer unitId;
    /**
     * 模块id
     */
  @ApiModelProperty(value = "模块id")
  private Integer modelId;


}
