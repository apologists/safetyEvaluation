package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.example.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 原因节点表实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Cause对象", description = "原因节点表")
@TableName("cause")
public class Cause extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId
  private Integer causeId;
    /**
     * 变量id
     */
  @ApiModelProperty(value = "变量id")
  private Integer variableId;
    /**
     * 变量名称
     */
  @ApiModelProperty(value = "变量名称")
  private String variableName;
    /**
     * 原因节点名称
     */
  @ApiModelProperty(value = "原因节点名称")
  private String causeName;
    /**
     * 正向
     */
  @ApiModelProperty(value = "正向")
  private String straight;
    /**
     * 负向
     */
  @ApiModelProperty(value = "负向")
  private String burden;
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
     * 模型id
     */
  @ApiModelProperty(value = "模型id")
  private Integer modelId;


}
