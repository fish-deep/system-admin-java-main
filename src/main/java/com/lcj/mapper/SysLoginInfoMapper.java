package com.lcj.mapper;

import com.lcj.entity.SysLoginInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 系统访问记录 Mapper 接口
 * @author pmwy
 */
public interface SysLoginInfoMapper extends BaseMapper<SysLoginInfo> {

    int clearAllData();
}
