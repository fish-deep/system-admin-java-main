package com.lcj.service.impl;

import com.lcj.entity.SysRole;
import com.lcj.entity.SysRoleMenu;
import com.lcj.mapper.SysRoleMapper;
import com.lcj.service.SysRoleMenuService;
import com.lcj.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public boolean insertRole(SysRole sysRole) {
        sysRoleMapper.insertRole(sysRole);
        Long roleId = sysRole.getId();
        List<SysRoleMenu> list = new ArrayList<>();
        for (Long menuId : sysRole.getMenuIds()) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(roleId);
            list.add(sysRoleMenu);
        }
        return sysRoleMenuService.saveBatch(list);
    }
}
