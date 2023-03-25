package com.lcj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcj.service.*;
import com.lcj.utils.RedisUtil;
import org.springframework.web.bind.ServletRequestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class BaseController<T> {

    @Resource
    HttpServletRequest request;

    @Resource
    RedisUtil redisUtil;

    @Resource
    SysUserService sysUserService;

    @Resource
    SysMenuService sysMenuService;

    @Resource
    SysRoleService sysRoleService;

    @Resource
    SysRoleMenuService sysRoleMenuService;

    @Resource
    SysUserRoleService sysUserRoleService;

    @Resource
    SysDeptService sysDeptService;

    @Resource
    SysLoginInfoService sysLoginInfoService;

    @Resource
    SysOperateLogService sysOperateLogService;

    @Resource
    GoodTypeService goodTypeService;

    @Resource
    GoodInfoService goodInfoService;

    @Resource
    GoodStockService goodStockService;


    @Resource
    AccessRegisterService accessRegisterService;

    @Resource
    AccessReturnService accessReturnService;

    @Resource
    LeaveApplyService leaveApplyService;

    @Resource
    SysNoticeService sysNoticeService;

    public Page<T> getPage(){
        int pageNum = ServletRequestUtils.getIntParameter(request, "pageNum", 1);
        int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
        return new Page<T>(pageNum, pageSize);
    }

}
