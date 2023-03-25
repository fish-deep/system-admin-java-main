package com.lcj.service;

import com.lcj.entity.HealthClock;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 打卡健康表 服务类
 * </p>
 */
public interface HealthClockService extends IService<HealthClock> {

    int checkClockToday(String name);
}
