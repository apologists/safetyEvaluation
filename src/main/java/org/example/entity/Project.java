package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.example.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 项目表实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Project对象", description = "项目表")
@TableName("project")
public class Project extends BaseEntity {

    private static final long serialVersionUID = 1L;

  private Integer projectId;
    /**
     * 项目名称
     */
  @ApiModelProperty(value = "项目名称")
  private String projectName;


}
