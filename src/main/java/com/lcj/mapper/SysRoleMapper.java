package com.lcj.mapper;

import com.lcj.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 *  角色 Mapper 接口
 *  @author pmwy
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    int insertRole(SysRole sysRole);
}
