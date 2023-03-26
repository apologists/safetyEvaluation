package org.example.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.experimental.Accessors;
import org.example.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.example.common.CommonDto;
/**
 * 风险等级说明数据传输对象实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RiskGrade对象", description = "风险等级说明")
public class RiskGradeDTO extends CommonDto {

	@ApiModelProperty(value = "riskGradeId")
	@TableId
	private Integer riskGradeId;
		/**
		 * 说明
		 */
			@ApiModelProperty(value = "说明")
					private String riskGradeDesc;
					private String riskGrade;
		/**
		 * 行动要求
		 */
			@ApiModelProperty(value = "行动要求")
					private String actionAsk;
		/**
		 * 是否可接受
		 */
			@ApiModelProperty(value = "是否可接受")
					private String tolerate;
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
		 * 频率id
		 */
			@ApiModelProperty(value = "频率id")
					private Integer frequencyId;
		/**
		 * 风险后果id
		 */
			@ApiModelProperty(value = "风险后果id")
					private Integer riskConsequenceId;
		/**
		 * 颜色
		 */
			@ApiModelProperty(value = "颜色")
					private String colour;
		/**
		 * 风险数字
		 */
			@ApiModelProperty(value = "风险数字")
					private String gradeNum;


		}
