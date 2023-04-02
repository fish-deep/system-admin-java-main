package com.lcj.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表 实体类
 * @author pmwy
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUser对象", description="")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 52162801L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户账号")
    @NotBlank(message = "用户账号不能为空！")
    private String username;
    @ApiModelProperty(value = "用户密码")
    @NotBlank(message = "用户密码不能为空")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "手机号码")
    @NotBlank(message = "手机号不能为空！")
    private String phoneNumber;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "部门id")
    @NotNull(message = "归属部门不能为空！")
    private Long deptId;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "身份")
    private String remark;
    @ApiModelProperty(value = "是否启用")
    private Integer status;

    @TableLogic
    @ApiModelProperty(value = "逻辑删除")
    private Integer isDelete;

    @Version
    @ApiModelProperty(value = "乐观锁")
    private Integer version;

}
