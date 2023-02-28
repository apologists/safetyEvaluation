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
 * @since 2022-08-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Risk对象", description = "Risk对象")
public class RiskDTO extends CommonDto {

					private Integer riskId;
					private String grade;
					private String severity;
					private String personnel;
					private String property;
					private String environment;
					private String reputation;
					private String laws;
					private String lockout;
					private Integer projectId;


		}
