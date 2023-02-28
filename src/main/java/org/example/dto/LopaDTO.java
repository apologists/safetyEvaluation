package org.example.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import org.example.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Lopa对象", description = "Lopa对象")
public class LopaDTO extends CommonDto {

					private Integer lopaId;
					private String scenarioDesc;
					private String consequencesDesc;
					private String eventIe;
					private String ignitionProbability;
					private String exposureProbability;
					private String lethalityRate;
					private String protectionDesc;
					private String protectionRate;
					private String accidentRate;
					private String allowAccidentRate;
						@TableField("SIL_grade")
					private String silGrade;
					private Integer projectId;
	                private Long level;

		}
