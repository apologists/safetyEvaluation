package org.example.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import org.example.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.example.common.CommonDto;
/**
 * 项目表数据传输对象实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Project对象", description = "项目表")
public class ProjectDTO extends CommonDto {

					private Integer projectId;
		/**
		 * 项目名称
		 */
			@ApiModelProperty(value = "项目名称")
					private String projectName;


		}
