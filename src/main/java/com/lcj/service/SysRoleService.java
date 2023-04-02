package com.lcj.service;

import com.lcj.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 角色表
 * @author pmwy
 */

public interface SysRoleService extends IService<SysRole> {

    boolean insertRole(SysRole sysRole);
}
