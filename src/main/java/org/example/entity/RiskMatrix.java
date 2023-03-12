package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class RiskMatrix {
    /**
     * 表头
     */
    @ApiModelProperty(value = "表头")
    private Map<Integer,String> tables;

    /**
     * 表数据
     */
    @ApiModelProperty(value = "表数据,key代表风险程度,value代表该列数据")
    private List<Map<Integer,RiskGrade>> data;
}
