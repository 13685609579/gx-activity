package com.ruoyi.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.exam.domain.UnitManage;
import com.ruoyi.exam.mapper.UnitManageMapper;
import com.ruoyi.exam.service.UnitManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.mapper.SysDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 考生单位管理表 服务实现类
 * </p>
 *
 * @author hougq
 * @since 2023-10-08
 */
@Service
public class UnitManageServiceImpl extends ServiceImpl<UnitManageMapper, UnitManage> implements UnitManageService {

    @Autowired
    private UnitManageMapper unitManageMapper;

    @Autowired
    private SysDeptMapper deptMapper;

    /**
     * 获取所有单位
     * @return
     */
    @Override
    public List<SysDept> getUnitNames() {
        SysDept dept = new SysDept();
        return deptMapper.selectDeptList(dept);
    }
}
