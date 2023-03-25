package com.lcj.service.impl;

import com.lcj.entity.SysLoginInfo;
import com.lcj.mapper.SysLoginInfoMapper;
import com.lcj.service.SysLoginInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统访问记录 服务实现类
 */
@Service
public class SysLoginInfoServiceImpl extends ServiceImpl<SysLoginInfoMapper, SysLoginInfo> implements SysLoginInfoService {

    @Resource
    private SysLoginInfoMapper sysLoginInfoMapper;

    @Override
    public int clearAllInfo() {
        return sysLoginInfoMapper.clearAllData();
    }

}
