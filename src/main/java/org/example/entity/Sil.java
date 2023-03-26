package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.example.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * sil验算实体类
 *
 * @author AI
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Sil对象", description = "sil验算")
@TableName("sil")
public class Sil extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId
  private Integer silId;
    /**
     * 回路
     */
  @ApiModelProperty(value = "回路")
  private String circuit;
    /**
     * 类别
     */
  @ApiModelProperty(value = "类别")
  private String silType;
    /**
     * 名称
     */
  @ApiModelProperty(value = "名称")
  private String silName;
    /**
     * 结构
     */
  @ApiModelProperty(value = "结构")
  private String structure;
    /**
     * 变量1
     */
  @ApiModelProperty(value = "变量1")
  private String var1;
    /**
     * 变量2
     */
  @ApiModelProperty(value = "变量2")
  private String va2;
    /**
     * 变量3
     */
  @ApiModelProperty(value = "变量3")
  private String var3;
    /**
     * 变量4
     */
  @ApiModelProperty(value = "变量4")
  private String var4;
    /**
     * 变量5
     */
  @ApiModelProperty(value = "变量5")
  private String var5;
  private Integer projectId;

  /**
   * 变量5
   */
  @ApiModelProperty(value = "变量6")
  private String var6;


  /**
   * 变量5
   */
  @ApiModelProperty(value = "变量7")
  private String var7;
    /**
     * sil等级
     */
  @ApiModelProperty(value = "sil等级")
  private String silGrade;
    /**
     * 系统PFD
     */
  @ApiModelProperty(value = "系统PFD")
  @TableField("system_PFD")
  private String systemPfd;
    /**
     * 单元id
     */
  @ApiModelProperty(value = "单元id")
  private Integer unitId;


}
