package com.fanchen;

import com.fanchen.entity.SysUser;
import com.fanchen.service.SysUserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
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
