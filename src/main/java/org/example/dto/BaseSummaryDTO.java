package org.example.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import org.example.base.BaseEntity;
import java.time.LocalDateTime;
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
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "BaseSummary对象", description = "BaseSummary对象")
public class BaseSummaryDTO extends CommonDto {

					private String name;
					private String device;
					private String unit;
					private Integer projectId;
					private String baseBeginTime;
					private String baseEndTime;
						@TableField("analyticalMethod")
	private String analyticalMethod;
					private String remark;
					private Integer baseId;


		}
