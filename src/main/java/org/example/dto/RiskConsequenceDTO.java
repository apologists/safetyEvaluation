package org.example.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import org.example.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.example.common.CommonDto;
/**
 * 风险后果说明表数据传输对象实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RiskConsequence对象", description = "风险后果说明表")
public class RiskConsequenceDTO extends CommonDto {

					private Integer riskConsequenceId;
		/**
		 * 代码
		 */
			@ApiModelProperty(value = "代码")
					private String riskConsequencecolCode;
		/**
		 * 名称
		 */
			@ApiModelProperty(value = "名称")
					private String riskConsequencecolName;
		/**
		 * 说明
		 */
			@ApiModelProperty(value = "说明")
					private String riskConsequencecolDesc;
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
