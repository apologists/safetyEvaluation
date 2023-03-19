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
 * lopa分析数据传输对象实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Lopa对象", description = "lopa分析")
public class LopaDTO extends CommonDto {

					private Integer lopaId;
		/**
		 * 场景描述
		 */
			@ApiModelProperty(value = "场景描述")
					private String sceneDesc;
		/**
		 * 初始事件发生频率
		 */
			@ApiModelProperty(value = "初始事件发生频率")
					private String eventVate;
		/**
		 * 使用条件
		 */
			@ApiModelProperty(value = "使用条件")
					private String conditon;
		/**
		 * 初始事件IPL
		 */
			@ApiModelProperty(value = "初始事件IPL")
					private String eventIpl;
		/**
		 * 风险类别
		 */
			@ApiModelProperty(value = "风险类别")
					private String lopaType;
		/**
		 * 等级
		 */
			@ApiModelProperty(value = "等级")
					private String lopaGrade;
		/**
		 * 描述
		 */
			@ApiModelProperty(value = "描述")
					private String lopaDesc;
		/**
		 * 可接受频率
		 */
			@ApiModelProperty(value = "可接受频率")
					private String tolerate;
		/**
		 * 条件修正
		 */
			@ApiModelProperty(value = "条件修正")
					private String reviseCondition;
		/**
		 * 修正系数
		 */
			@ApiModelProperty(value = "修正系数")
					private String reviseRate;
		/**
		 * 独立保护层描述
		 */
			@ApiModelProperty(value = "独立保护层描述")
					private String protectDesc;
		/**
		 * 独立保护层
		 */
			@ApiModelProperty(value = "独立保护层")
					private String protect;
		/**
		 * 分析结论
		 */
			@ApiModelProperty(value = "分析结论")
					private String conclusion;
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


		}
