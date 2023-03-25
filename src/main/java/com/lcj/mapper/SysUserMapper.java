package com.lcj.mapper;

import com.lcj.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 *  Mapper 接口
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<Long> getMenuIds(Long userId);

    List<SysUser> listByMenuId(Long menuId);

    int register(SysUser user);
}
