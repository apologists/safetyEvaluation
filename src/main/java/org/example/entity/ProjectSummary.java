package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.base.BaseEntity;

import java.util.List;
import java.util.Map;

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
public class ProjectSummary extends BaseEntity {

    private static final long serialVersionUID = 1L;

  private Integer projectId;
    /**
     * 项目名称
     */
  @ApiModelProperty(value = "项目名称")
  private String projectName;


    /**
     * 单元map，key表示单元id，value表示该单元id下的模型id集合
     */
  private Map<Unit,List<Model>> unitMap;


}
