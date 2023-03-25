package com.lcj.controller;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcj.annotation.Log;
import com.lcj.common.lang.Result;
import com.lcj.entity.SysLoginInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 系统访问记录
 */
@RestController
@RequestMapping("/sys/loginInfo")
public class SysLoginInfoController extends BaseController<SysLoginInfo> {

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('sys:login:list')")
    public Result list(String ip, String username, Integer status, String start, String end){
        LambdaQueryWrapper<SysLoginInfo> wrapper = Wrappers.lambdaQuery(SysLoginInfo.class);
        wrapper.like(StrUtil.isNotBlank(ip), SysLoginInfo::getIp, ip);
        wrapper.like(StrUtil.isNotBlank(username), SysLoginInfo::getUsername, username);
        wrapper.eq(status != null, SysLoginInfo::getStatus, status);
        if (StrUtil.isNotBlank(start) && StrUtil.isNotBlank(end)){
            DateTime a = DateUtil.parse(start + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            DateTime b = DateUtil.parse(end + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
            wrapper.between(SysLoginInfo::getLoginTime, a, b);
        }
        wrapper.orderByDesc(SysLoginInfo::getLoginTime);
        Page<SysLoginInfo> page = sysLoginInfoService.page(getPage(), wrapper);
        return Result.succ(page);
    }

    @DeleteMapping
    @Log(title = "登录日志", businessType = "删除日志")
    @PreAuthorize("hasAnyAuthority('sys:login:delete')")
    public Result delete(Long[] ids){
        boolean remove = sysLoginInfoService.removeByIds(Arrays.asList(ids));
        if (remove){
            return Result.succ("删除成功！");
        }else{
            return Result.fail("删除失败！");
        }
    }

    @PostMapping
    @Log(title = "菜单管理", businessType = "清空日志")
    @PreAuthorize("hasAnyAuthority('sys:login:clear')")
    public Result clear(){
        sysLoginInfoService.clearAllInfo();
        return Result.succ("所有数据已清楚！");
    }

}

