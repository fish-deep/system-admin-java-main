package com.lcj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcj.entity.SysUser;


public interface SysUserService extends IService<SysUser> {

    SysUser getByUsername(String username);

    String getUserAuthority(String username);

    void clearUserAuthority(String username);

    void clearUserAuthorityByRoleId(Long roleId);

    void clearUserAuthorityByMenuId(Long menuId);

    boolean registerUser(String username, String password, Integer roleType, String registerCode, Long deptId, String phoneNumber);
}
