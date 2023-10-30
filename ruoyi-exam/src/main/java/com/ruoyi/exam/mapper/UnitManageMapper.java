package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.UnitManage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.exam.domain.vo.CandidateClassHourVo;

import java.util.List;

/**
 * <p>
 * 考生单位管理表 Mapper 接口
 * </p>
 *
 * @author hougq
 * @since 2023-10-08
 */
public interface UnitManageMapper extends BaseMapper<UnitManage> {

    /**
     * 获取所有单位
     * @param candidateClassHourVo
     * @return
     */
    public List<UnitManage> selectUnitList(CandidateClassHourVo candidateClassHourVo);

    /**
     * 获取所有单位
     * @return
     */
    public List<UnitManage> selectUnitAllList();

}
