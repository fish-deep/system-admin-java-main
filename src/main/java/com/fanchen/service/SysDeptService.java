package com.fanchen.service;

import com.fanchen.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 部门表 服务类
 */
public interface SysDeptService extends IService<SysDept> {

    List<SysDept> getAllDept(boolean flag);
}
