package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.example.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 后果节点表实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Consequence对象", description = "后果节点表")
@TableName("consequence")
public class Consequence extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId
  private Integer consequenceId;
    /**
     * 后果节点
     */
  @ApiModelProperty(value = "后果节点")
  private String consequenceName;
    /**
     * 变量id
     */
  @ApiModelProperty(value = "变量id")
  private Integer variableId;
    /**
     * 变量名称
     */
  @ApiModelProperty(value = "变量名称")
  private String varialeName;
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
