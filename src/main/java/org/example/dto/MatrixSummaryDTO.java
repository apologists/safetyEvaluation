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
@ApiModel(value = "MatrixSummary对象", description = "MatrixSummary对象")
public class MatrixSummaryDTO extends CommonDto {

					private Integer matrixId;
					private String matrixName;
					private String matrixDesc;
					private Integer horizontal;
					private Integer longitudinal;
					private Integer projectId;


		}
