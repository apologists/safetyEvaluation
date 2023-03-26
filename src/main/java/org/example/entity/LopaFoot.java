package org.example.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Data
public class LopaFoot implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer lopaId;
    /**
     * 风险类别
     */
    @ApiModelProperty(value = "风险类别")
    private String lopaType;
    /**
     * 等级
     */
    @ApiModelProperty(value = "等级")
    private String lopaGrade;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String lopaDesc;
    /**
     * 可接受频率
     */
    @ApiModelProperty(value = "可接受频率")
    private String tolerate;
    /**
     * 条件修正
     */
    @ApiModelProperty(value = "条件修正")
    private String reviseCondition;
    /**
     * 修正系数
     */
    @ApiModelProperty(value = "修正系数")
    private String reviseRate;
    /**
     * 独立保护层描述
     */
    @ApiModelProperty(value = "独立保护层描述")
    private String protectDesc;
    /**
     * 独立保护层
     */
    @ApiModelProperty(value = "独立保护层")
    private String protect;
    /**
     * 分析结论
     */
    @ApiModelProperty(value = "分析结论")
    private String conclusion;

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
