package com.lcj.service.impl;

import com.lcj.entity.HealthClock;
import com.lcj.mapper.HealthClockMapper;
import com.lcj.service.HealthClockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 打卡健康表 服务实现类
 * </p>
 *
 */
@Service
public class HealthClockServiceImpl extends ServiceImpl<HealthClockMapper, HealthClock> implements HealthClockService {

    @Resource
    private HealthClockMapper healthClockMapper;

    @Override
    public int checkClockToday(String name) {
        return healthClockMapper.checkClockToday(name);
    }

}
