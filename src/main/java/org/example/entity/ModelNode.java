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
 * 模型实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Model对象", description = "模型")
@TableName("model")
@Accessors(chain = true)
public class ModelNode extends TreeNode {

    private static final long serialVersionUID = 1L;

  private Integer projectId;
  private Integer unitId;
  private Integer modelId;

}
