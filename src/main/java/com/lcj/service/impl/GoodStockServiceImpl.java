package com.lcj.service.impl;

import com.lcj.entity.GoodStock;
import com.lcj.mapper.GoodStockMapper;
import com.lcj.service.GoodStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 物资出入库表 服务实现类
 */
@Service
public class GoodStockServiceImpl extends ServiceImpl<GoodStockMapper, GoodStock> implements GoodStockService {

}
