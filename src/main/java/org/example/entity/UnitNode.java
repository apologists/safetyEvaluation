package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.poi.ss.formula.functions.T;
import org.example.base.BaseEntity;

import java.util.List;

/**
 * 单元表实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Unit对象", description = "单元表")
@TableName("unit")
@Accessors(chain = true)
public class UnitNode extends TreeNode {

    private static final long serialVersionUID = 1L;


  private Integer projectId;
  private Integer unitId;

}
