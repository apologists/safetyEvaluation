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
 * @since 2022-10-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SilSummary对象", description = "SilSummary对象")
public class SilSummaryDTO extends CommonDto {

					private Long silSummaryId;
						@TableField("D1")
	private Double d1;
						@TableField("D2")
	private Double d2;
						@TableField("Du")
	private Double Du;
						@TableField("S")
	private Double s;
						@TableField("B")
	private Double b;
						@TableField("Ti")
	private Double Ti;
					private String projectId;
					private Double silSum;


		}
