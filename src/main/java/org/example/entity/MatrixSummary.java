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
 * @since 2022-08-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MatrixSummary对象", description = "MatrixSummary对象")
@TableName("matrix_summary")
public class MatrixSummary extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId(value = "matrix_id", type = IdType.AUTO)
  private Integer matrixId;
  private String matrixName;
  private String matrixDesc;
  private Integer horizontal;
  private Integer longitudinal;
  private Integer projectId;


}
