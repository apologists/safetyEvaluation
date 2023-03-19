package org.example.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.experimental.Accessors;
import org.example.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.example.common.CommonDto;
/**
 * 数据传输对象实体类
 *
 * @author AI
 * @since 2023-02-28
 */
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Hazop对象", description = "Hazop对象")
public class HazopDTO extends CommonDto {

		/**
		 * hazopId
		 */
			@ApiModelProperty(value = "hazopId")
					private Integer hazopId;
		/**
		 * hazop名称
		 */
			@ApiModelProperty(value = "hazop名称")
					private String hazopName;
		/**
		 * 参数
		 */
			@ApiModelProperty(value = "参数")
					private String parameter;
		/**
		 * 参数引导词
		 */
			@ApiModelProperty(value = "参数引导词")
					private String parameterDesc;
		/**
		 * 偏离描述
		 */
			@ApiModelProperty(value = "偏离描述")
					private String deviateDesc;
		/**
		 * 原因
		 */
			@ApiModelProperty(value = "原因")
					private String cause;
		/**
		 * 后果
		 */
			@ApiModelProperty(value = "后果")
					private String consequence;
						@TableField("F0")
	private String f0;
		/**
		 * 现有措施
		 */
			@ApiModelProperty(value = "现有措施")
					private String measure;
		/**
		 * Si
		 */
			@ApiModelProperty(value = "Si")
					private String riskSi;
		/**
		 * Li
		 */
			@ApiModelProperty(value = "Li")
					private String riskLi;
					private String riskRi;
						@TableField("Fs")
	private String Fs;
					private String riskS;
					private String riskL;
					private String riskR;
		/**
		 * 建议编号
		 */
			@ApiModelProperty(value = "建议编号")
					private String proposeNum;
		/**
		 * 建议项
		 */
			@ApiModelProperty(value = "建议项")
					private String propose;
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
		 * hazop颜色
		 */
		@ApiModelProperty(value = "hazop颜色")
		private Integer hazopColor1;

		/**
	 	* hazop2颜色
	 	*/
		@ApiModelProperty(value = "hazop2颜色")
		private Integer hazopColor2;
		}
