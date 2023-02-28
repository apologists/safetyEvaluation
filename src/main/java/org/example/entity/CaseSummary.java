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
 * @since 2022-10-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CaseSummary对象", description = "CaseSummary对象")
@TableName("case_summary")
public class CaseSummary extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId(value = "case_id", type = IdType.AUTO)
  private Integer caseId;
  private String pullOffNode;
  private String deviation;
  private String abnormalCauses;
  private String adverseOutComes;
  private String relationShips;
  private String riskSeverity;
  private String riskGrade;
  private String existingMeasures;
  private String suggestedActions;
  private String severity;
  private String frequencyLevel;

  @Override
  public int hashCode() {
    int result = getCaseId().hashCode();
    //17是死值, jdk建议用17
    result = 17 * result + getCaseId().hashCode();
    return result;
  }

}
