package com.ruoyi.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.StatisticalAnalysis;
import com.ruoyi.exam.mapper.StatisticalAnalysisMapper;
import com.ruoyi.exam.service.StatisticalAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 统计分析 服务实现类
 * </p>
 *
 * @author yxp
 * @since 2023-10-08
 */
@Service
public class StatisticalAnalysisServiceImpl extends ServiceImpl<StatisticalAnalysisMapper, StatisticalAnalysis> implements StatisticalAnalysisService {

    @Autowired
    private StatisticalAnalysisMapper statisticalAnalysisMapper;

    /**
     * 统计分析列表
     * @param statisticalAnalysis
     * @return
     */
    @Override
    public List<StatisticalAnalysis> selectStatisticalAnalysisList(StatisticalAnalysis statisticalAnalysis) {
        List<StatisticalAnalysis> statisticalAnalysisList = statisticalAnalysisMapper.selectStatisticalAnalysisList(statisticalAnalysis);
        if(null != statisticalAnalysisList && statisticalAnalysisList.size()>0){
            statisticalAnalysisList.stream().forEach(m->{
                if(StringUtils.isNotBlank(m.getStartTime()) && StringUtils.isNotEmpty(m.getStartTime())
                && StringUtils.isNotBlank(m.getEndTime()) && StringUtils.isNotEmpty(m.getEndTime())){
                    m.setExam_phase(m.getStartTime()+"~"+m.getEndTime());
                }
            });
        }
        return statisticalAnalysisList;
    }

}
