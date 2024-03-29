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
 * 后果节点表数据传输对象实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Consequence对象", description = "后果节点表")
@Accessors(chain = true)
public class ConsequenceDTO extends CommonDto {

	@ApiModelProperty(value = "consequenceId")
	private Integer consequenceId;
		/**
		 * 后果节点
		 */
			@ApiModelProperty(value = "后果节点")
					private String consequenceName;
		/**
		 * 变量id
		 */
			@ApiModelProperty(value = "变量id")
					private Integer variableId;
		/**
		 * 变量名称
		 */
			@ApiModelProperty(value = "变量名称")
					private String variableName;

	/**
	 * 变量名称
	 */
	@ApiModelProperty(value = "变量名称")
	private String variableNameEn;
		/**
		 * 正向
		 */
			@ApiModelProperty(value = "正向")
					private String straight;
		/**
		 * 负向
		 */
			@ApiModelProperty(value = "负向")
					private String burden;
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


		}
