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
 * @since 2022-10-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CaseSummary对象", description = "CaseSummary对象")
public class CaseSummaryDTO extends CommonDto {

					private Integer caseId;
					private String pullOffNode;
					private String deviation;
					private String abnormalCauses;
					private String adverseOutComes;
					private String relationShips;
					private String riskSeverity;
					private String riskGrade;
					private String existingMeasures;
					private String suggestedActions;
					private String severity;
					private String frequencyLevel;


		}
