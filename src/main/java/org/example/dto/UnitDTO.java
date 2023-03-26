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
 * 单元表数据传输对象实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Unit对象", description = "单元表")
@Accessors(chain = true)
public class UnitDTO extends CommonDto {

	@ApiModelProperty(value = "unitId")
	private Integer unitId;
		/**
		 * 单元名称
		 */
			@ApiModelProperty(value = "单元名称")
					private String unitName;
		/**
		 * 创建者
		 */
			@ApiModelProperty(value = "创建者")
					private String creater;
		/**
		 * 装置
		 */
			@ApiModelProperty(value = "装置")
					private String installation;
		/**
		 * 项目名称
		 */
			@ApiModelProperty(value = "项目名称")
					private Integer projectId;


		}
