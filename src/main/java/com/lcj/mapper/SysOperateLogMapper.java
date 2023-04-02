package com.lcj.mapper;

import com.lcj.entity.SysOperateLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 操作日志记录 Mapper 接口
 * @author pmwy
 */
public interface SysOperateLogMapper extends BaseMapper<SysOperateLog> {

    int clearAll();
}
