package org.example.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import org.example.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.example.common.CommonDto;
/**
 * 数据传输对象实体类
 *
 * @author AI
 * @since 2022-08-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ProjectSummary对象", description = "ProjectSummary对象")
public class ProjectSummaryDTO extends CommonDto {

					private Long projectId;
					private String projectName;
					private String projectDesc;
					private String projectImage;


		}
