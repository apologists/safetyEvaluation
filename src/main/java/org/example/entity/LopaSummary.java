package org.example.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class LopaSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<LopaHead> headList;
    private List<LopaFoot> footList;
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
