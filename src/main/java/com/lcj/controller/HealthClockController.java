package com.lcj.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcj.annotation.Log;
import com.lcj.common.lang.Result;
import com.lcj.entity.HealthClock;
import com.lcj.entity.SysUser;
import com.lcj.entity.SysUserRole;
import com.lcj.service.HealthClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * <p>
 * 打卡健康表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/health/clock")
public class HealthClockController extends BaseController<HealthClock> {

    @Value("${system.code.serviceRole}")
    private Long serviceRole;
    @Autowired
    private HealthClockService healthClockService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('health:clock:list')")
    public Result list(Integer healthType, Integer middleHigh, Integer diagnosis, Integer returnInfo, Long deptId, Principal principal){
        String username = principal.getName();
        SysUser sysUser = (SysUser) redisUtil.get("User:" + username);
        List<SysUserRole> sysUserRoles = sysUserRoleService.list(new QueryWrapper<SysUserRole>().eq("user_id", sysUser.getId()));
        boolean flag = false;
        for (SysUserRole userRole : sysUserRoles) {
            if (userRole.getRoleId().equals(serviceRole)) {
                flag = true;
                break;
            }
        }
        QueryWrapper<HealthClock> wrapper = new QueryWrapper<>();
        if (flag){
            wrapper.eq(healthType != null, "health_type", healthType);
            wrapper.eq(middleHigh != null, "middle_high", middleHigh);
            wrapper.eq(diagnosis != null, "diagnosis", diagnosis);
            wrapper.eq(returnInfo != null, "return_info", returnInfo);
            wrapper.eq(deptId != null, "dept_id", deptId);
        }else {
            wrapper.eq("username", username);
        }
        wrapper.orderByDesc("create_time");
        Page<HealthClock> page = healthClockService.page(getPage(), wrapper);
        return Result.succ(page);
    }

    @GetMapping
    public Result check(Principal principal){
        String name = principal.getName();
        int count = healthClockService.checkClockToday(name);
        return count > 0 ? Result.succ("今日已打卡，请不要重复打卡！") : Result.succ("allow");
    }

    @PostMapping
    @Log(title = "健康打卡", businessType = "添加打卡")
    @PreAuthorize("hasAnyAuthority('health:clock:save')")
    public Result save(@Validated @RequestBody HealthClock healthClock){
        boolean save = healthClockService.save(healthClock);
        if (save){
            return Result.succ("打卡成功");
        }else {
            return Result.fail("打卡失败");
        }
    }

}

