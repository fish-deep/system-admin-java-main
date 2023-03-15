package com.fanchen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fanchen.entity.Health;
import com.fanchen.mapper.HealthMapper;
import com.fanchen.service.HealthService;
import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl extends ServiceImpl<HealthMapper, Health> implements HealthService {
}
