package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.example.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 频率说明表实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Frequency对象", description = "频率说明表")
@TableName("frequency")
public class Frequency extends BaseEntity {

    private static final long serialVersionUID = 1L;

  private Integer frequencyId;
    /**
     * 代码
     */
  @ApiModelProperty(value = "代码")
  private String frequencyCode;
    /**
     * 名称
     */
  @ApiModelProperty(value = "名称")
  private String frequencyName;
    /**
     * 说明
     */
  @ApiModelProperty(value = "说明")
  private String frequencyDesc;
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
