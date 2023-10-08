package com.ruoyi.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.exam.domain.StatisticalAnalysis;

import java.util.List;

/**
 * <p>
 * 统计分析 服务类
 * </p>
 *
 * @author yxp
 * @since 2023-10-08
 */
public interface StatisticalAnalysisService extends IService<StatisticalAnalysis> {

    /**
     * 统计分析列表
     * @param statisticalAnalysis
     * @return
     */
    public List<StatisticalAnalysis> selectStatisticalAnalysisList(StatisticalAnalysis statisticalAnalysis);
}
