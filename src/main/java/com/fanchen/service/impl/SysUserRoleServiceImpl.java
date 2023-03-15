package com.fanchen.service.impl;

import com.fanchen.entity.SysUserRole;
import com.fanchen.mapper.SysUserRoleMapper;
import com.fanchen.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}
