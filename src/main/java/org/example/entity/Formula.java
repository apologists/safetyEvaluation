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
 * @since 2022-08-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Formula对象", description = "Formula对象")
@TableName("formula")
public class Formula extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId(value = "formula_id", type = IdType.AUTO)
    private Integer formulaId;
    private String formula;
    private Integer projectId;
    private String formulaTo;
    private String formulaFrom;

  @Override
  public int hashCode() {
    int result = getFormulaFrom().hashCode();
    //17是死值, jdk建议用17
    result = 17 * result + getFormulaFrom().hashCode();
    return result;
  }
}
