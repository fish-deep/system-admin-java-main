package com.fanchen.mapper;

import com.fanchen.entity.SysLoginInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 系统访问记录 Mapper 接口
 */
public interface SysLoginInfoMapper extends BaseMapper<SysLoginInfo> {

    int clearAllData();
}
