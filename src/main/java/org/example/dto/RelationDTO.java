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
 * @since 2022-08-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Relation对象", description = "Relation对象")
public class RelationDTO extends CommonDto {

					private Integer frequencyId;
					private String frequencyLevel;
					private String frequencyValue;
					private String frequencyDesc;
					private Integer projectId;


		}
