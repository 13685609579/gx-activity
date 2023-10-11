package com.ruoyi.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.exam.domain.vo.CandidateClassHourVo;
import com.ruoyi.exam.domain.vo.StatisticalAnalysisDetailsVo;
import com.ruoyi.exam.domain.vo.StatisticalAnalysisVo;

import java.util.List;

/**
 * <p>
 * 统计分析 Mapper 接口
 * </p>
 *
 * @author yxp
 * @since 2023-10-08
 */
public interface StatisticalAnalysisMapper extends BaseMapper<StatisticalAnalysisVo> {

    /**
     * 统计分析列表
     * @param candidateClassHourVo
     * @return
     */
    public List<CandidateClassHourVo> selectStatisticalAnalysisList(CandidateClassHourVo candidateClassHourVo);

    /**
     * 统计分析详情
     * @param detailsVo
     * @return
     */
    public List<StatisticalAnalysisDetailsVo> getStatisticalAnalysisDetails(StatisticalAnalysisDetailsVo detailsVo);

}
