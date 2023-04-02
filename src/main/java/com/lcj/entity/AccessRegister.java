package com.lcj.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lcj.common.enums.OutTimeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 出入登记表
 * @author pmwy
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AccessRegister对象", description="出入登记表")
public class AccessRegister implements Serializable {

    private static final long serialVersionUID = 715712647L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    

    @ApiModelProperty(value = "姓名")
    @NotNull(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "手机号码")
    @NotBlank(message = "手机号码不能为空")
    private String phone;

    @ApiModelProperty(value = "出入类型（1：出校 0：入校）")
    @NotNull(message = "出入类型不能为空")
    private Integer type;


    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "请假表id")
    @NotBlank(message = "外键id不能为空")
    private Integer leaveId;

    @ApiModelProperty(value = "请假开始时间")
    @NotBlank(message = "请假开始时间不能为空")
    private Date startTime;

    @ApiModelProperty(value = "请假结束时间")
    @NotBlank(message = "请假结束时间不能为空")
    private Date endTime;



    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)//在插入时由数据库自动填充
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty(value = "判断请假状态 1：请假未开始  2：请假中  3：未归  4：完成请假")
    @TableField(exist = false)
    private OutTimeType isOutTime;


}
