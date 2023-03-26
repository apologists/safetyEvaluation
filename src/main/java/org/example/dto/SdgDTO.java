package org.example.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.experimental.Accessors;
import org.example.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.example.common.CommonDto;
/**
 * sdg拉偏表数据传输对象实体类
 *
 * @author AI
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Sdg对象", description = "sdg拉偏表")
@Accessors(chain = true)
public class SdgDTO extends CommonDto {

	@ApiModelProperty(value = "sdgId")
	private Integer sdgId;
		/**
		 * 变量名称
		 */
			@ApiModelProperty(value = "变量名称")
					private String variableName;
		/**
		 * 拉偏方向
		 */
			@ApiModelProperty(value = "拉偏方向")
						@TableField("pullDirection")
		private String pullDirection;

	/**
	 * 变量名称
	 */
	@ApiModelProperty(value = "变量名称")
	private String variableNameEn;


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
		 * 项目id
		 */
			@ApiModelProperty(value = "项目id")
					private Integer projectId;


		}
