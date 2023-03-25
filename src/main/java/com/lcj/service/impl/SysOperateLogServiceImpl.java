package com.lcj.service.impl;

import com.lcj.entity.SysOperateLog;
import com.lcj.mapper.SysOperateLogMapper;
import com.lcj.service.SysOperateLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 操作日志记录 服务实现类
 */
@Service
public class SysOperateLogServiceImpl extends ServiceImpl<SysOperateLogMapper, SysOperateLog> implements SysOperateLogService {

    @Resource
    private SysOperateLogMapper sysOperateLogMapper;

    @Override
    public int clearAll() {
        return sysOperateLogMapper.clearAll();
    }
}
