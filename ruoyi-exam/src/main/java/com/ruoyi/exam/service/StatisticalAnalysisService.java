package com.ruoyi.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.exam.domain.vo.CandidateClassHourVo;
import com.ruoyi.exam.domain.vo.StatisticalAnalysisDetailsVo;
import com.ruoyi.exam.domain.vo.StatisticalAnalysisVo;

import java.util.List;

/**
 * <p>
 * 统计分析 服务类
 * </p>
 *
 * @author yxp
 * @since 2023-10-08
 */
public interface StatisticalAnalysisService extends IService<StatisticalAnalysisVo> {

    /**
     * 统计分析列表
     * @param candidateClassHourVo
     * @return
     */
    public List<StatisticalAnalysisVo> selectStatisticalAnalysisList(CandidateClassHourVo candidateClassHourVo);

    /**
     * 统计分析详情
     * @param detailsVo
     * @return
     */
    public List<StatisticalAnalysisDetailsVo> getStatisticalAnalysisDetails(StatisticalAnalysisDetailsVo detailsVo);

}
