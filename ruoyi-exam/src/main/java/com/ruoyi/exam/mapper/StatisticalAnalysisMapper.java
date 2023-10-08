package com.ruoyi.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.exam.domain.StatisticalAnalysis;

import java.util.List;

/**
 * <p>
 * 统计分析 Mapper 接口
 * </p>
 *
 * @author yxp
 * @since 2023-10-08
 */
public interface StatisticalAnalysisMapper extends BaseMapper<StatisticalAnalysis> {

    /**
     * 统计分析列表
     * @param statisticalAnalysis
     * @return
     */
    public List<StatisticalAnalysis> selectStatisticalAnalysisList(StatisticalAnalysis statisticalAnalysis);

}
