package org.example.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.experimental.Accessors;
import org.example.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.example.common.CommonDto;
/**
 * 模型数据传输对象实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Model对象", description = "模型")
@Accessors(chain = true)
public class ModelDTO extends CommonDto {

	@ApiModelProperty(value = "modelId")
	private Integer modelId;
		/**
		 * 模型名称
		 */
			@ApiModelProperty(value = "模型名称")
					private String modelName;
		/**
		 * 模型编号
		 */
			@ApiModelProperty(value = "模型编号")
					private String modelNum;
		/**
		 * 工艺类型
		 */
			@ApiModelProperty(value = "工艺类型")
					private String processType;
		/**
		 * 操作过程类别
		 */
			@ApiModelProperty(value = "操作过程类别")
					private String operationProcessType;
		/**
		 * 设备类型
		 */
			@ApiModelProperty(value = "设备类型")
					private String equipmentType;
		/**
		 * 设备材质类型
		 */
			@ApiModelProperty(value = "设备材质类型")
					private String equimentMaterialType;
		/**
		 * 压力
		 */
			@ApiModelProperty(value = "压力")
					private String pressure;
		/**
		 * 温度
		 */
			@ApiModelProperty(value = "温度")
					private String temperature;
		/**
		 * 流量
		 */
			@ApiModelProperty(value = "流量")
					private String rateFlow;
		/**
		 * 物料
		 */
			@ApiModelProperty(value = "物料")
					private String matter;
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
