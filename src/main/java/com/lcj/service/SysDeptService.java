package com.lcj.service;

import com.lcj.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 部门表 服务类
 */
public interface SysDeptService extends IService<SysDept> {

    List<SysDept> getAllDept(boolean flag);
}
