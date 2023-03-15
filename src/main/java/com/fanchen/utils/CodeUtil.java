package com.fanchen.utils;

import com.fanchen.common.lang.Const;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "system.code")//读取配置文件中的注册码设置
public class CodeUtil {
    private String studentCode;
    private String teacherCode;
    private Long studentRole;
    private Long teacherRole;
    public Long switchRole(Integer roleType, String registerCode){
        switch (roleType){
            case Const.ROLE_STUDENT:
                return registerCode.equals(studentCode) ? studentRole : -1L;
            case Const.ROLE_TEACHER:
                return registerCode.equals(teacherCode) ? teacherRole : -1L;
            default:
                return -1L;
        }
    }
}
