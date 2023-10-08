package com.ruoyi.exam.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.exam.domain.CandidateInfo;
import com.ruoyi.exam.domain.StatisticalAnalysis;
import com.ruoyi.exam.service.StatisticalAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * <p>
 * 统计分析 前端控制器
 * </p>
 *
 * @author yxp
 * @since 2023-10-08
 */
@RestController
@RequestMapping("/statistical-analysis")
public class StatisticalAnalysisController extends BaseController {

    @Autowired
    private StatisticalAnalysisService statisticalAnalysisService;

    /**
     * 统计分析列表
     * @param statisticalAnalysis
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo list(StatisticalAnalysis statisticalAnalysis)
    {
        startPage();
        List<StatisticalAnalysis> list = statisticalAnalysisService.selectStatisticalAnalysisList(statisticalAnalysis);
        return getDataTable(list);
    }

}
