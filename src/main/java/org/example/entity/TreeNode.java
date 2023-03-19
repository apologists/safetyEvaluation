package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.example.base.BaseEntity;

import java.util.List;

/**
 * 变量表实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@ApiModel(value = "Variable对象", description = "变量表")
@TableName("variable")
@Accessors(chain = true)
public class TreeNode  {

    private static final long serialVersionUID = 1L;

  private String id;
  /**
   * 项目名称
   */
  @ApiModelProperty(value = "项目名称")
  private String name;

  private String type;

  private List<TreeNode> children;


}
