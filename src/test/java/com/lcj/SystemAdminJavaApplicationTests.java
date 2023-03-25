package com.lcj;

import com.lcj.entity.SysUser;
import com.lcj.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SystemAdminJavaApplicationTests {

    @Resource
    private SysUserService sysUserService;

    @Test
    void contextLoads() {
        List<SysUser> userList = sysUserService.list();
        for (SysUser user : userList) {
            System.out.println(user);
        }
    }




}
