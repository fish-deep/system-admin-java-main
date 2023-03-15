package com.fanchen.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("health_info")
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="健康打卡", description="健康打卡表")
public class Health implements Serializable {
    private static final long serialVersionUID = 2L;
    @TableId(value = "health_id")
    private Integer healthId;
    private String userId;
    private Integer healthState;
    private Integer vaccineNum;
    private Float temperature;
    private String createTime;
    private String location;
    private String route;
    private Integer deleteFlag;
}
