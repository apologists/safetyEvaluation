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
 * @since 2022-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Hazop对象", description = "Hazop对象")
@TableName("hazop")
public class Hazop extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId(value = "hazop_id", type = IdType.AUTO)
  private Integer hazopId;
  private String pullOffNode;
  private String deviation;
  private String abnormalCauses;
  private String adverseOutComes;
  private Integer projectId;
  private String relationShips;
  private String riskSeverity;
  private String riskGrade;
  private String existingMeasures;
  private String suggestedActions;


}
