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
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AdverseOutComes对象", description = "AdverseOutComes对象")
@TableName("adverse_out_comes")
public class AdverseOutComes extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId(value = "adverse_out_comes_id", type = IdType.AUTO)
  private Integer adverseOutComesId;
  private String pullOffNode;
  private String deviate;
  private String variableName;
  private String deviation;
  private String evolutionaryPath;
  private String adverseOutComes;
  private Integer projectId;


}
