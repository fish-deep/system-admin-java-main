package com.fanchen.mapper;

import com.fanchen.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 *  Mapper 接口
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    int insertRole(SysRole sysRole);
}
