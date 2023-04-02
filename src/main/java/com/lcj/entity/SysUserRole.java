package com.lcj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  用户角色表 实体类
 * @author pmwy
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUserRole对象", description="")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 9119652L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    @NotBlank(message = "id不能为空")
    private Long id;

    @ApiModelProperty("用户id")
    @NotBlank(message = "用户id不能为空")
    private Long userId;

    @ApiModelProperty("角色id")
    @NotBlank(message = "角色id不能为空")
    private Long roleId;


}
