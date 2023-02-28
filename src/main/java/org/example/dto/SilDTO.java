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
 * @since 2022-09-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Sil对象", description = "Sil对象")
public class SilDTO extends CommonDto {

					private Long id;
						@TableField("SIL_Grade")
	private String silGrade;
					private Long probability;
					private Long factorHigth;
					private Long level;
					private Long factorLow;


		}
