package com.fanchen.mapper;

import com.fanchen.entity.SysOperateLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 操作日志记录 Mapper 接口
 */
public interface SysOperateLogMapper extends BaseMapper<SysOperateLog> {

    int clearAll();
}
