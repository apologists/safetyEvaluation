package org.example.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.experimental.Accessors;
import org.example.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.example.common.CommonDto;
/**
 * 变量表数据传输对象实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Variable对象", description = "变量表")
@Accessors(chain = true)
public class VariableDTO extends CommonDto {

	@ApiModelProperty(value = "variableId")
	private Integer variableId;
		/**
		 * 变量中文名称
		 */
			@ApiModelProperty(value = "变量中文名称")
					private String variableNameCn;
		/**
		 * 变量英文名称
		 */
			@ApiModelProperty(value = "变量英文名称")
					private String variableNameEn;
		/**
		 * 项目id
		 */
			@ApiModelProperty(value = "项目id")
					private Integer projectId;
		/**
		 * 单元id
		 */
			@ApiModelProperty(value = "单元id")
					private Integer unitId;
		/**
		 * 模型id
		 */
			@ApiModelProperty(value = "模型id")
					private Integer modelId;
		/**
		 * 变量编号
		 */
			@ApiModelProperty(value = "变量编号")
					private String variableNum;


		}
