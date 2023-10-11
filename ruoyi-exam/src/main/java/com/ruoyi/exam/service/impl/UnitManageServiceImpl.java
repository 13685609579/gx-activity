package com.ruoyi.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.exam.domain.UnitManage;
import com.ruoyi.exam.mapper.UnitManageMapper;
import com.ruoyi.exam.service.UnitManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    /**
     * 获取所有单位
     * @return
     */
    @Override
    public List<UnitManage> getUnitNames() {
        List<UnitManage> unitManageList = unitManageMapper.selectUnitAllList();
        return unitManageList;
    }
}
