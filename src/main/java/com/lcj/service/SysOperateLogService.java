package com.lcj.service;

import com.lcj.entity.SysOperateLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 操作日志记录 服务类
 */
public interface SysOperateLogService extends IService<SysOperateLog> {

    int clearAll();
}
