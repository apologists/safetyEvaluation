package org.example.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import org.example.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.example.common.CommonDto;

import javax.validation.constraints.NotNull;

/**
 * 用户表数据传输对象实体类
 *
 * @author AI
 * @since 2020-06-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "User对象", description = "用户表")
public class UserDTO extends CommonDto {

    private Integer userId;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
     * 类型，（0-未设置（也就是未开通），1=管理员、2=学生）
     */
    @ApiModelProperty(value = "类型，（0-未设置（也就是未开通），1=管理员、2=学生）")
    private Integer type;
    /**
     * 用户专业
     */
    @ApiModelProperty(value = "用户专业")
    private String userSpecialty;
    /**
     * 用户院
     */
    @ApiModelProperty(value = "用户院")
    private String userCollege;
    /**
     * 班级id
     */
    @ApiModelProperty(value = "班级id")
    private Integer clazzId;
    /**
     * 班级
     */
    @ApiModelProperty(value = "班级")
    private String clazz;
    /**
     * 性别（1-男，0-女）
     */
    @ApiModelProperty(value = "性别（1-男，0-女）")
    private Integer sex;
    /**
     * 账户（学号 / 工号）
     */
    @ApiModelProperty(value = "账户（学号 / 工号）")
    private String userNo;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 头像照片
     */
    @ApiModelProperty(value = "头像照片")
    private String photo;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String mobile;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 违规次数，超过3次，本学期不可进行预约
     */
    @ApiModelProperty(value = "违规次数，超过3次，本学期不可进行预约")
    private Integer violation;
    /**
     * 上次上机时间（自主上机）
     */
    @ApiModelProperty(value = "上次上机时间（自主上机）")
    private LocalDateTime lastTime;
    /**
     * 预约下次时间
     */
    @ApiModelProperty(value = "预约下次时间")
    private LocalDateTime nextTime;
    /**
     * 上机时长
     */
    @ApiModelProperty(value = "上机时长")
    private Integer useHour;


}
