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
 * @since 2022-09-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Sil对象", description = "Sil对象")
@TableName("sil")
public class Sil extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;
  @TableField("SIL_Grade")
  private String silGrade;
  private Long probability;
  private Long factorHigth;
  private Long level;
  private Long factorLow;


}
