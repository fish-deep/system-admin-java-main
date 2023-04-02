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
 * 角色菜单表 实体类
 * @author pmwy
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRoleMenu对象", description="")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 5151967L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    @NotBlank(message = "id不能为空")
    private Long id;
    @ApiModelProperty(value = "角色id")
    @NotBlank(message = "角色id不能为空")
    private Long roleId;
    @ApiModelProperty(value = "菜单id")
    @NotBlank(message = "菜单id不能为空")
    private Long menuId;


}
