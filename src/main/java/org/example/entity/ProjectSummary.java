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
@ApiModel(value = "ProjectSummary对象", description = "ProjectSummary对象")
@TableName("project_summary")
public class ProjectSummary extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId(value = "project_id", type = IdType.AUTO)
  private Long projectId;
  private String projectName;
  private String projectDesc;
  private String projectImage;


}
