package com.fanchen.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.fanchen.annotation.Log;
import com.fanchen.common.lang.Result;
import com.fanchen.entity.Health;

import com.fanchen.mapper.HealthMapper;
import com.fanchen.mapper.SysUserMapper;
import com.fanchen.service.HealthService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/health")
public class HealthController {
    @Resource
    private HealthService healthService;

    @Resource
    private HealthMapper healthMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    //学生添加每日健康信息
    @Log(title = "学生添加每日打卡")
    @PostMapping("/insertHealthInfo")
    public Result insertHealthInfo(@RequestBody Health health) {
        System.out.println(health.getUserId());
        //判断今天是否已提交信息
        SimpleDateFormat dayFormat1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat dayFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = dayFormat1.format(new Date());
        String endTime = dayFormat2.format(new Date());
        QueryWrapper<Health> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", health.getUserId());
        queryWrapper.eq("delete_flag",1);
        queryWrapper.between("create_time", startTime, endTime);
        List<Health> list = healthService.list(queryWrapper);
        if (list.isEmpty()) {
            //添加
            System.out.println("添加：");
            if (healthService.save(health)) {
                return Result.succ("添加成功");
            } else {
                return Result.fail("添加失败");
            }
        } else {
            //修改
            System.out.println("修改：");
            if (healthService.saveOrUpdate(health,new QueryWrapper<Health>().eq("health_id", list.get(0).getHealthId()))) {
                return Result.succ("修改成功");
            } else {
                return Result.fail("修改失败");
            }
        }
    }

    //学生查询每日健康记录
    @GetMapping("/getDayHealthInfo")
    public Result getDayHealthInfo(@RequestParam(value = "userId") String userId, @RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize, @RequestParam(defaultValue = "") String location,
                                   @RequestParam(defaultValue = "") String datetime1, @RequestParam(defaultValue = "") String datetime2) {
        QueryWrapper<Health> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("delete_flag", 1);
        queryWrapper.orderByDesc("create_time");
        if (!StrUtil.isEmpty(location)) {
            queryWrapper.like("location", location);
        }
        if (!StrUtil.isEmpty(datetime1) && !StrUtil.isEmpty(datetime2)) {
            queryWrapper.between("create_time", datetime1, datetime2);
        }
        return Result.succ(healthMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper));
    }

    //根据学号获取学生健康信息条数
    @GetMapping("/getHealthNumByUserId")
    public int getHealthNumByUserId(@RequestParam(value = "userId") String userId) {
        return (int) healthService.count();
    }

    //根据健康信息id删除记录
    @PostMapping("/deleteInfoByHealthId")
    public Result deleteInfoByHealthId(@RequestBody Health health) {
        health.setDeleteFlag(0);
        if(healthService.saveOrUpdate(health)){
            return Result.succ("成功删除");
        }
        return Result.fail("删除失败");
    }

/*    //老师获取学生健康信息记录
    @GetMapping("/getStuHealthInfoS")
    public Result getStuHealthInfoS(@RequestParam(value = "userId") String userId, @RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize, @RequestParam(defaultValue = "") String inputClass,
                                    @RequestParam(defaultValue = "") String inputName,
                                    @RequestParam(defaultValue = "") String datetime1, @RequestParam(defaultValue = "") String datetime2){

        //根据id找班级id，根据classId找学生，根据userId找健康记录
        User user = userService.getOne(new QueryWrapper<User>().eq("user_id", userId));
        String classIds = user.getClassId();
        List<String> typeList = new ArrayList<>();
        if (classIds != null) {
            String[] typeStr = classIds.split(",");
            typeList.addAll(Arrays.asList(typeStr));
        }
        QueryWrapper<Health> queryWrapper = new QueryWrapper<>();
//        if(!StrUtil.isEmpty(inputClass)){
//            queryWrapper.eq("class_id",inputClass);
//        }
        if(!StrUtil.isEmpty(inputName)){
            queryWrapper.like("user_id",inputName);
        }
        if (!StrUtil.isEmpty(datetime1) && !StrUtil.isEmpty(datetime2)) {
            queryWrapper.between("create_time", datetime1, datetime2);
        }
        queryWrapper.eq("delete_flag",1);
//        queryWrapper.in("class_id",typeList);
        return Result.success(healthMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper));
    }

    //老师查看每日健康信息提交进度
    @GetMapping("/members3")
    public Result members3(@RequestParam(value = "userId") String userId){
        User user = userService.getOne(new QueryWrapper<User>().eq("user_id", userId));
        String classIds = user.getClassId();
        List<String> typeList = new ArrayList<>();
        if (classIds != null) {
            String[] typeStr = classIds.split(",");
            typeList.addAll(Arrays.asList(typeStr));
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("class_id",typeList);
        int num1 = (int) userService.count(queryWrapper);//全部人数

        return Result.success(CollUtil.newArrayList(num1));
    }*/

    //图表1，疫苗
    @GetMapping("/members1")
    public Result members1() {
        List<Health> list = healthService.list();
        int n0 = 0, n1 = 0, n2 = 0, n3 = 0;
        for (Health health : list) {
            int vaccineNum = health.getVaccineNum();
            switch (vaccineNum) {
                case 0:
                    n0 += 1;
                    break;
                case 1:
                    n1 += 1;
                    break;
                case 2:
                    n2 += 1;
                    break;
                case 3:
                    n3 += 1;
                    break;
                default:
                    break;
            }
        }
        return Result.succ(CollUtil.newArrayList(n0, n1, n2, n3));
    }

    //图表2，健康
    @GetMapping("/members2")
    public Result members2() {
        List<Health> list = healthService.list();
        int n0 = 0, n1 = 0, n2 = 0;
        for (Health health : list) {
            int healthState = health.getHealthState();
            switch (healthState) {
                case 0:
                    n0 += 1;
                    break;
                case 1:
                    n1 += 1;
                    break;
                case 2:
                    n2 += 1;
                    break;
                default:
                    break;
            }
        }
        return Result.succ(CollUtil.newArrayList(n0, n1, n2));
    }
}
