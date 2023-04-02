package com.lcj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcj.entity.SysUser;

/**
 * 用户表
 * @author pmwy
 */

public interface SysUserService extends IService<SysUser> {

    /**
     * 通过用户名字获取用户
     * @param username {@link String}
     * @return  {@link SysUser}
     */
    SysUser getByUsername(String username);

    /**
     *
     * @param username
     * @return
     */
    String getUserAuthority(String username);

    void clearUserAuthority(String username);

    void clearUserAuthorityByRoleId(Long roleId);

    void clearUserAuthorityByMenuId(Long menuId);

    boolean registerUser(String username, String password, Integer roleType, String registerCode, Long deptId, String phoneNumber);
}
