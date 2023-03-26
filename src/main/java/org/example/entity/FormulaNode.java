package org.example.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormulaNode {
    /**
     * 变量公式右边
     */
    @ApiModelProperty(value = "变量公式右边")
    private String formulaRight;
    /**
     * 变量公式左边
     */
    @ApiModelProperty(value = "变量公式左边")
    private String formulaLeft;

    private Boolean dashes;
}
