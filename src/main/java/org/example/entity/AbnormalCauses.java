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
 * 实体类
 *
 * @author AI
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AbnormalCauses对象", description = "AbnormalCauses对象")
@TableName("abnormal_causes")
public class AbnormalCauses extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId(value = "abnormal_causes_id", type = IdType.AUTO)
  private Integer abnormalCausesId;
  private String consequenceNode;
  private String deviate;
  @TableField("Variable_name")
  private String variableName;
  private String deviation;
  private String evolutionaryPath;
  private Integer projectId;
  private String abnormalCauses;


}
