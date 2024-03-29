package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.example.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 风险后果说明表实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RiskConsequence对象", description = "风险后果说明表")
@TableName("risk_consequence")
public class RiskConsequence extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId
  private Integer riskConsequenceId;
    /**
     * 代码
     */
  @ApiModelProperty(value = "代码")
  private String riskConsequencecolCode;
    /**
     * 名称
     */
  @ApiModelProperty(value = "名称")
  private String riskConsequencecolName;
    /**
     * 说明
     */
  @ApiModelProperty(value = "说明")
  private String riskConsequencecolDesc;
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


}
