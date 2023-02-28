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
@ApiModel(value = "Variable对象", description = "Variable对象")
@TableName("variable")
public class Variable extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId(value = "variable_id", type = IdType.AUTO)
  private Integer variableId;
  private String variableCn;
  private String variableEn;
  private Integer projectId;


}
