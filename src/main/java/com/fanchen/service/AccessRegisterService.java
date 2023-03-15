package com.fanchen.service;

import com.fanchen.entity.AccessRegister;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 出入登记表 服务类
 */
public interface AccessRegisterService extends IService<AccessRegister> {

    boolean addRegister(AccessRegister accessRegister);
}
