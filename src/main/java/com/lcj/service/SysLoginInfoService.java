package com.lcj.service;

import com.lcj.entity.SysLoginInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 系统访问记录 服务类
 * @author pmwy
 */
public interface SysLoginInfoService extends IService<SysLoginInfo> {

    int clearAllInfo();
}
