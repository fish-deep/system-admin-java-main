package com.lcj.service;

import com.lcj.common.dto.NavMenu;
import com.lcj.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 菜单表
 * @author pmwy
 */

public interface SysMenuService extends IService<SysMenu> {

    List<NavMenu> getCurrentUserNav();

    List<SysMenu> getCurrentUserMenu();
}
