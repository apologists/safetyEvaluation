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
 * 实体类
 *
 * @author AI
 * @since 2022-08-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RiskGrade对象", description = "RiskGrade对象")
@TableName("risk_grade")
public class RiskGrade extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId(value = "risk_grade_id", type = IdType.AUTO)
  private Integer riskGradeId;
  private Integer riskGradeCode;
  private Integer riskGradeColor;
  private String riskGradeDesc;
  private String riskGrade;
  private String riskGradeMeasure;
  private String term;
  private String remark;
  private Integer projectId;
  private Integer horizontal;
  private Integer longitudinal;
  private String severity;
  private String frequencyLevel;


}
