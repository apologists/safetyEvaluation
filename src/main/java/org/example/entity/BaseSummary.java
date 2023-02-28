package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import org.example.base.BaseEntity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author AI
 * @since 2022-08-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "BaseSummary对象", description = "BaseSummary对象")
@TableName("base_summary")
public class BaseSummary extends BaseEntity {

    private static final long serialVersionUID = 1L;

  private String name;
  private String device;
  private String unit;
  private Integer projectId;
  private LocalDateTime baseBeginTime;
  private LocalDateTime baseEndTime;
  @TableField("analyticalMethod")
  private String analyticalMethod;
  private String remark;
  @TableId(value = "base_id", type = IdType.AUTO)
  private Integer baseId;


}
