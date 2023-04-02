package com.lcj.controller;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcj.annotation.Log;
import com.lcj.common.lang.Result;
import com.lcj.entity.AccessRegister;
import com.lcj.vo.LeaveBaseIVO.LeaveQueryIVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * 出入登记表 前端控制器
 */
@RestController
@RequestMapping("/access/register")
public class AccessRegisterController extends BaseController<AccessRegister> {

/*    @PostMapping
    @Log(title = "出入登记", businessType = "添加记录")
    @PreAuthorize("hasAnyAuthority('access:register:save')")//在方法执行前进行校验，确定是否有权限
    public Result save(@Validated @RequestBody AccessRegister accessRegister, Principal principal){
        accessRegister.setCreateBy(principal.getName());
        boolean flag = accessRegisterService.addRegister(accessRegister);
        return flag ? Result.succ("登记成功") : Result.fail("登记失败");
    }*/

    @GetMapping("/list")
    //@PreAuthorize("hasAnyAuthority('access:register:list')")
    @PreAuthorize("hasAnyAuthority('good:info:list')")
    @ApiOperation("查询出行列表")
    public Result list(LeaveQueryIVO ivo){
        LambdaQueryWrapper<AccessRegister> wrapper = Wrappers.lambdaQuery(AccessRegister.class);
        wrapper.like(StrUtil.isNotBlank(ivo.getName()), AccessRegister::getName, ivo.getName());
        wrapper.eq(ivo.getType() != null, AccessRegister::getType, ivo.getType());
        if (StrUtil.isNotBlank(ivo.getStart()) && StrUtil.isNotBlank(ivo.getEnd())){
            DateTime a = DateUtil.parse(ivo.getStart() + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            DateTime b = DateUtil.parse(ivo.getEnd() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
            wrapper.between(AccessRegister::getCreateTime, a, b);
        }
        wrapper.orderByDesc(AccessRegister::getCreateTime);
        Page<AccessRegister> page = accessRegisterService.page(getPage(), wrapper);
        return Result.succ(page);
    }

}

