package com.lcj.mapper;

import com.lcj.entity.HealthClock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 打卡健康表 Mapper 接口
 * </p>
 */
public interface HealthClockMapper extends BaseMapper<HealthClock> {

    int checkClockToday(String name);
}
