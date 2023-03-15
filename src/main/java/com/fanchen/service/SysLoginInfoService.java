package com.fanchen.service;

import com.fanchen.entity.SysLoginInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 系统访问记录 服务类
 */
public interface SysLoginInfoService extends IService<SysLoginInfo> {

    int clearAllInfo();
}
