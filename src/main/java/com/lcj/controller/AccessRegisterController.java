package com.lcj.controller;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcj.annotation.Log;
import com.lcj.common.enums.OutTimeType;
import com.lcj.common.lang.Result;
import com.lcj.entity.AccessRegister;
import com.lcj.entity.LeaveApply;
import com.lcj.vo.LeaveBaseIVO.LeaveQueryIVO;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
    @PreAuthorize("hasAnyAuthority('access:register:list')")
    @ApiOperation("查询出行列表")
    public Result list(LeaveQueryIVO ivo) {
        LambdaQueryWrapper<AccessRegister> wrapper = Wrappers.lambdaQuery(AccessRegister.class);
        wrapper.like(StrUtil.isNotBlank(ivo.getName()), AccessRegister::getName, ivo.getName());
        wrapper.eq(ivo.getType() != null, AccessRegister::getType, ivo.getType());
        if (StrUtil.isNotBlank(ivo.getStartTime()) && StrUtil.isNotBlank(ivo.getEndTime())) {
            DateTime a = DateUtil.parse(ivo.getStartTime(), "yyyy-MM-dd HH:mm:ss");
            DateTime b = DateUtil.parse(ivo.getEndTime(), "yyyy-MM-dd HH:mm:ss");
            wrapper.between(AccessRegister::getCreateTime, a, b);
        }
        wrapper.orderByDesc(AccessRegister::getCreateTime);
        Page<AccessRegister> page = accessRegisterService.page(getPage(), wrapper);
        List<AccessRegister> records = page.getRecords();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = LocalDateTime.parse(ivo.getStartTime(),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endTime = LocalDateTime.parse(ivo.getEndTime(),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        records.forEach(record -> {
                if (now.isBefore(startTime)) {
                    record.setIsOutTime(OutTimeType.WAIT_LEAVE);
                } else if (now.isAfter(startTime) && now.isBefore(endTime)) {
                    record.setIsOutTime(OutTimeType.ON_LEAVE);
                } else if (now.isAfter(endTime)) {
                    if (ivo.getType() == 0) {
                        record.setIsOutTime(OutTimeType.FINISH_LEAVE);
                    } else if (ivo.getType() == 1) {
                        record.setIsOutTime(OutTimeType.OUT_LEAVE);
                    }
                }
            }
        );
        page.setRecords(records);
        return Result.succ(page);
    }
}

