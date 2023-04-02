package com.lcj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lcj.common.lang.Const;
import com.lcj.entity.SysMenu;
import com.lcj.entity.SysRole;
import com.lcj.entity.SysUser;
import com.lcj.entity.SysUserRole;
import com.lcj.mapper.SysUserMapper;
import com.lcj.service.SysMenuService;
import com.lcj.service.SysRoleService;
import com.lcj.service.SysUserRoleService;
import com.lcj.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcj.utils.CodeUtil;
import com.lcj.utils.RedisUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private CodeUtil codeUtil;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    public SysUser getByUsername(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("status", 1);
        return getOne(wrapper);
    }

    @Override
    public String getUserAuthority(String username) {
        SysUser sysUser = (SysUser) redisUtil.get("User:" + username);
        Long userId = sysUser.getId();
        String authority = "";
        if (redisUtil.hasKey("GrantedAuthority:" + sysUser.getUsername())) {
            authority = redisUtil.get("GrantedAuthority:" + sysUser.getUsername()).toString();
        } else {
            QueryWrapper<SysRole> wrapper = new QueryWrapper<SysRole>()
                    .inSql("id", "select role_id from sys_user_role where user_id = " + userId)
                    .eq("status", 1);
            List<SysRole> roles = sysRoleService.list(wrapper);
            if (roles.size() > 0) {
                String roleCodes = roles.stream().map(role -> "ROLE_" + role.getCode()).
                        collect(Collectors.joining(","));
                authority = roleCodes.concat(",");
            }
            List<Long> menuIds = sysUserMapper.getMenuIds(userId);
            if (menuIds.size() > 0) {
                List<SysMenu> menus = sysMenuService.listByIds(menuIds);
                String menuPerms = menus.stream().map(menu -> "" + menu.getPerms())
                        .collect(Collectors.joining(","));
                authority = authority.concat(menuPerms);
            }
            redisUtil.set("GrantedAuthority:" + sysUser.getUsername(), authority);
        }
        return authority;
    }

    @Override
    public void clearUserAuthority(String username) {
        redisUtil.del("GrantedAuthority:" + username);
    }

    @Override
    public void clearUserAuthorityByRoleId(Long roleId) {
        List<SysUser> sysUserList = list(new QueryWrapper<SysUser>()
                .inSql("id", "select user_id from sys_user_role where role_id = " + roleId));
        sysUserList.forEach(user -> clearUserAuthority(user.getUsername()));
    }

    @Override
    public void clearUserAuthorityByMenuId(Long menuId) {
        List<SysUser> sysUserList = sysUserMapper.listByMenuId(menuId);
        sysUserList.forEach(user -> clearUserAuthority(user.getUsername()));
    }

    @Override
    public boolean registerUser(String username, String password, Integer roleType, String registerCode, Long deptId, String phoneNumber) {
        Long roleId = codeUtil.switchRole(roleType, registerCode);


        if (roleId != -1L){
            SysUser user = new SysUser();
            user.setUsername(username);
            //密码加密
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setAvatar(Const.DEFAULT_IMG);
            user.setDeptId(deptId);
            user.setPhoneNumber(phoneNumber);
            sysUserMapper.register(user);
            Long userId = user.getId();
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            sysUserRoleService.save(userRole);
            return true;
        }
        return false;
    }

}
