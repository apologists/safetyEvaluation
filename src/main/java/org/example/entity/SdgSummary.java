package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.example.base.BaseEntity;

import java.util.List;

/**
 * sdg拉偏表实体类
 *
 * @author AI
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Sdg对象", description = "sdg拉偏表")
@TableName("sdg")
@Accessors(chain = true)
public class SdgSummary extends BaseEntity {

    private static final long serialVersionUID = 1L;

  @TableId(value = "SdgSummaryId", type = IdType.AUTO)
  private Integer SdgSummaryId;
    /**
     * 变量名称
     */
  @ApiModelProperty(value = "变量名称")
  private String variableName;
    /**
     * 拉偏方向
     */
  @ApiModelProperty(value = "拉偏方向")
  @TableField("pullDirection")
  private String pullDirection;
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
    /**
     * 项目id
     */
  @ApiModelProperty(value = "项目id")
  private Integer projectId;

  /**
   * 不利后果
   */
  @ApiModelProperty(value = "不利后果")
  private List<Consequence> consequenceList;

  /**
   * 不利后果
   */
  @ApiModelProperty(value = "非正常原因")
  private List<Cause> causeList;

}
