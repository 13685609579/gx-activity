package com.ruoyi.exam.service;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.exam.domain.UnitManage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.exam.domain.vo.CandidateClassHourVo;

import java.util.List;

/**
 * <p>
 * 考生单位管理表 服务类
 * </p>
 *
 * @author yxp
 * @since 2023-10-08
 */
public interface UnitManageService extends IService<UnitManage> {

    /**
     * 获取所有单位
     * @return
     */
    public List<SysDept> getUnitNames();

    /**
     * 根据分页获取所有单位
     * @param candidateClassHourVo
     * @return
     */
    public List<UnitManage> selectUnitList(CandidateClassHourVo candidateClassHourVo);

}
