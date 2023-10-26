package com.ruoyi.exam.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.StatisticalAnalysisDetailsEntity;
import com.ruoyi.common.core.domain.entity.StatisticalAnalysisEntity;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.exam.domain.UnitManage;
import com.ruoyi.exam.domain.vo.CandidateClassHourVo;
import com.ruoyi.exam.domain.vo.StatisticalAnalysisDetailsVo;
import com.ruoyi.exam.domain.vo.StatisticalAnalysisVo;
import com.ruoyi.exam.service.StatisticalAnalysisService;
import com.ruoyi.exam.service.UnitManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

    @Autowired
    private UnitManageService unitManageService;


    /**
     * 统计分析列表
     * @param candidateClassHourVo
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo list(CandidateClassHourVo candidateClassHourVo)
    {
        startPage();
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if(100 != loginUser.getDeptId()){ //用户部门不是肥西县（deptId:100）的用户在考生审核获取本部门所有用户
            candidateClassHourVo.setUnitId(String.valueOf(loginUser.getDeptId()));
        }
        List<UnitManage> unitManageList = unitManageService.selectUnitList(candidateClassHourVo);
        candidateClassHourVo.setUnitManageList(unitManageList);
        List<StatisticalAnalysisVo> list = statisticalAnalysisService.selectStatisticalAnalysisList(candidateClassHourVo);
        TableDataInfo tableDataInfo = getDataTable(list);
        tableDataInfo.setTotal(new PageInfo(unitManageList).getTotal());
        return tableDataInfo;
    }

    /**
     * 统计分析详情
     * @param detailsVo
     * @return
     */
    @GetMapping("/getStatisticalAnalysisDetails")
    public TableDataInfo getStatisticalAnalysisDetails(StatisticalAnalysisDetailsVo detailsVo)
    {
        startPage();
        List<StatisticalAnalysisDetailsVo> list = statisticalAnalysisService.getStatisticalAnalysisDetails(detailsVo);
        return getDataTable(list);
    }

    /**
     * 统计分析-列表导出
     * @param response
     * @param candidateClassHourVo
     */
    @Log(title = "统计分析-导出列表", businessType = BusinessType.EXPORT)
    @PostMapping("/exportList")
    public void exportList(HttpServletResponse response, CandidateClassHourVo candidateClassHourVo)
    {
        List<StatisticalAnalysisVo> analysisVoList = statisticalAnalysisService.selectStatisticalAnalysisList(candidateClassHourVo);
        List<StatisticalAnalysisEntity> analysisEntityList = new ArrayList<StatisticalAnalysisEntity>();
        if(CollectionUtil.isNotEmpty(analysisVoList)){
            analysisVoList.stream().forEach(m->{
                StatisticalAnalysisEntity entity = new StatisticalAnalysisEntity();
                entity.setUnitName(m.getUnitName());
                entity.setCompleteCount(m.getCompleteCount());
                entity.setCompleteRate(m.getCompleteRate());
                entity.setUncompletedCount(m.getUncompletedCount());
                entity.setExamYear(m.getExamYear());
                analysisEntityList.add(entity);
            });
        }
        ExcelUtil<StatisticalAnalysisEntity> util = new ExcelUtil<StatisticalAnalysisEntity>(StatisticalAnalysisEntity.class);
        util.exportExcel(response, analysisEntityList, "统计分析数据");
    }

    /**
     * 统计分析-详情列表导出
     * @param response
     * @param detailsVo
     */
    @Log(title = "统计分析-详情列表导出", businessType = BusinessType.EXPORT)
    @PostMapping("/exportDetailsList")
    public void exportDetailsList(HttpServletResponse response, StatisticalAnalysisDetailsVo detailsVo)
    {
        List<StatisticalAnalysisDetailsVo> analysisDetailsVoList = statisticalAnalysisService.getStatisticalAnalysisDetails(detailsVo);
        List<StatisticalAnalysisDetailsEntity> analysisDetailsEntityList = new ArrayList<StatisticalAnalysisDetailsEntity>();
        if(CollectionUtil.isNotEmpty(analysisDetailsVoList)){
            analysisDetailsVoList.stream().forEach(m->{
                StatisticalAnalysisDetailsEntity entity = new StatisticalAnalysisDetailsEntity();
                entity.setCandidateName(m.getCandidateName());
                entity.setUnitName(m.getUnitName());
                entity.setMobile(m.getMobile());
                entity.setAcquiredHours(m.getAcquiredHours());
                entity.setExamYear(m.getExamYear());
                analysisDetailsEntityList.add(entity);
            });
        }
        ExcelUtil<StatisticalAnalysisDetailsEntity> util = new ExcelUtil<StatisticalAnalysisDetailsEntity>(StatisticalAnalysisDetailsEntity.class);
        util.exportExcel(response, analysisDetailsEntityList, "统计分析详情数据");
    }

}
