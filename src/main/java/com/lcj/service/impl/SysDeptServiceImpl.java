package com.lcj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lcj.entity.SysDept;
import com.lcj.mapper.SysDeptMapper;
import com.lcj.service.SysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门表 服务实现类
 */
@Service
@Transactional
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Override
    public List<SysDept> getAllDept(boolean flag) {
        List<SysDept> deptList;
        if (flag){
            QueryWrapper<SysDept> wrapper = new QueryWrapper<>();
            wrapper.select("dept_id", "parent_id", "dept_name");
            deptList = list(wrapper);
        }else {
            deptList = list();
        }
        return toTree(deptList, 0L);
    }

    private List<SysDept> toTree(List<SysDept> treeList, Long pid){
        List<SysDept> returnList = new ArrayList<>();
        for (SysDept parent : treeList) {
            if (pid.equals(parent.getParentId())){
                returnList.add(findChildren(parent, treeList));
            }
        }
        return returnList;
    }

    private SysDept findChildren(SysDept parent, List<SysDept> treeList){
        for (SysDept children : treeList) {
            if (parent.getDeptId().equals(children.getParentId())){
                if (parent.getChildren() == null){
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(findChildren(children, treeList));
            }
        }
        return parent;
    }

}
