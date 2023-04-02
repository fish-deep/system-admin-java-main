package com.lcj.service;

import com.lcj.entity.AccessReturn;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 未归用户表 服务类
 * @author pmwy
 */
public interface AccessReturnService extends IService<AccessReturn> {

    void deleteByName(String name);
}
