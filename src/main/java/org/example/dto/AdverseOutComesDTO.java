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
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AdverseOutComes对象", description = "AdverseOutComes对象")
public class AdverseOutComesDTO extends CommonDto {

					private Integer adverseOutComesId;
					private String pullOffNode;
					private String deviate;
					private String variableName;
					private String deviation;
					private String evolutionaryPath;
					private String adverseOutComes;
					private Integer projectId;


		}
