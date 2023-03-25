package com.lcj.service;

import com.lcj.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;


public interface SysRoleService extends IService<SysRole> {

    boolean insertRole(SysRole sysRole);
}
