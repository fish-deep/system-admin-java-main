package com.lcj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcj.entity.AccessRegister;
import com.lcj.entity.AccessReturn;
import com.lcj.mapper.AccessRegisterMapper;
import com.lcj.service.AccessRegisterService;
import com.lcj.service.AccessReturnService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 出入登记表 服务实现类
 */
@Service
@Transactional
public class AccessRegisterServiceImpl extends ServiceImpl<AccessRegisterMapper, AccessRegister> implements AccessRegisterService {

    @Resource
    private AccessRegisterMapper accessRegisterMapper;

    @Resource
    private AccessReturnService accessReturnService;

/*    @Override
    public boolean addRegister(AccessRegister accessRegister) {
        int insert = accessRegisterMapper.insert(accessRegister);
        if (insert > 0){
            if (accessRegister.getType() == 1){
                AccessReturn accessReturn = new AccessReturn();
                accessReturn.setName(accessRegister.getName());
                accessReturn.setCard(accessRegister.getCard());
                accessReturn.setDept(accessRegister.getDept());
                accessReturn.setPhone(accessRegister.getPhone());
                accessReturn.setRemark(accessRegister.getRemark());
                accessReturnService.save(accessReturn);
            }else {
                accessReturnService.deleteByName(accessRegister.getName());
            }
            return true;
        }
        return false;
    }*/
}
