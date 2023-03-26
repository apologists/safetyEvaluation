package org.example.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class LopaHead implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer lopaId;
    /**
     * 场景描述
     */
    @ApiModelProperty(value = "场景描述")
    private String sceneDesc;
    /**
     * 初始事件发生频率
     */
    @ApiModelProperty(value = "初始事件发生频率")
    private String eventVate;
    /**
     * 使用条件
     */
    @ApiModelProperty(value = "使用条件")
    private String conditon;
    /**
     * 初始事件IPL
     */
    @ApiModelProperty(value = "初始事件IPL")
    private String eventIpl;

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
