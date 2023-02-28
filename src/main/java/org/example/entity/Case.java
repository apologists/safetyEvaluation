package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.example.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 案例库表实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Case对象", description = "案例库表")
@TableName("case")
public class Case extends BaseEntity {

    private static final long serialVersionUID = 1L;

  private Integer caseId;
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
  private String equipmentMaterialType;
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
     * 工艺材质
     */
  @ApiModelProperty(value = "工艺材质")
  private String matter;
    /**
     * 偏差
     */
  @ApiModelProperty(value = "偏差")
  private String deviation;
    /**
     * 原因
     */
  @ApiModelProperty(value = "原因")
  private String cause;
    /**
     * 结果
     */
  @ApiModelProperty(value = "结果")
  private String consequence;
    /**
     * 措施
     */
  @ApiModelProperty(value = "措施")
  private String measure;
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
    /**
     * 模型id
     */
  @ApiModelProperty(value = "模型id")
  private Integer modelId;


}
