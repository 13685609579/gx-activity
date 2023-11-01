package com.ruoyi.exam.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.CandidateInfoEntity;
import com.ruoyi.common.core.domain.entity.StatisticalAnalysisEntity;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.exam.domain.CandidateInfo;
import com.ruoyi.exam.domain.CandidatePaperState;
import com.ruoyi.exam.domain.UnitManage;
import com.ruoyi.exam.domain.vo.CandidateClassHourVo;
import com.ruoyi.exam.domain.vo.CandidateSignUpVo;
import com.ruoyi.exam.domain.vo.StatisticalAnalysisVo;
import com.ruoyi.exam.service.CandidateInfoService;
import com.ruoyi.exam.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 考生信息表 前端控制器
 * </p>
 *
 * @author yxp
 * @since 2023-09-28
 */
@RestController
@RequestMapping("/candidate-info")
public class CandidateInfoController extends BaseController {

    @Autowired
    private CandidateInfoService candidateInfoService;

    /**
     * 当前考生信息
     * @param openId
     * @return
     */
    @GetMapping("/getCandidateInfo")
    @Anonymous
    public AjaxResult getCandidateInfo(HttpServletRequest request, String openId){
        DataUtils.appCheck(request);
        return success(candidateInfoService.getCandidateInfo(openId));
    }

    /**
     * 校验考生信息
     * @param openId
     * @return
     */
    @GetMapping("/verify")
    @Anonymous
    public AjaxResult verifyCandidateInfo(HttpServletRequest request, String openId){
        DataUtils.appCheck(request);
        AjaxResult ajaxResult = candidateInfoService.verifyCandidateInfo(openId);
        return ajaxResult;
    }

    /**
     * 注册考生信息
     * @param candidateInfo
     * @return
     */
    @Log(title = "注册考生信息", businessType = BusinessType.INSERT)
    @PostMapping(value = "/signInCandidateInfo")
    @Anonymous
    public AjaxResult signInCandidateInfo(@Validated @RequestBody CandidateInfo candidateInfo, HttpServletRequest request)
    {
        DataUtils.appCheck(request);
        return candidateInfoService.signInCandidateInfo(candidateInfo);
    }

    /**
     * 首页重要信息
     * @param openId
     * @return
     */
    @GetMapping("/importantInformation")
    @Anonymous
    public AjaxResult importantInformation(HttpServletRequest request, String openId){
        DataUtils.appCheck(request);
        return success(candidateInfoService.importantInformation(openId));
    }

    /**
     * 校验能否参加考试（当前时间是否在考试日期范围内）
     * @param openId
     * @return
     */
    @GetMapping("/verifyTakeTestStatus")
    @Anonymous
    public AjaxResult verifyTakeTestStatus(HttpServletRequest request, String openId){
        DataUtils.appCheck(request);
        return success(candidateInfoService.verifyTakeTestStatus(openId));
    }

    /**
     * 个人信息-提交
     * @param candidateInfo
     * @return
     */
    @Log(title = "个人信息-提交", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/updateCandidateInfo")
    @Anonymous
    public AjaxResult updateCandidateInfo(@Validated @RequestBody CandidateInfo candidateInfo){
        return toAjax(candidateInfoService.updateCandidateInfo(candidateInfo));
    }

    /**
     * 获取考生信息列表
     * @param candidateInfo
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo list(CandidateInfo candidateInfo)
    {
        startPage();
        List<CandidateInfo> list = candidateInfoService.selectCandidateInfoList(candidateInfo);
        return getDataTable(list);
    }

    /**
     * 考生审核-列表导出
     * @param response
     * @param candidateInfoEntity
     */
    @Log(title = "考生审核-列表导出", businessType = BusinessType.EXPORT)
    @PostMapping("/exportList")
    public void exportList(HttpServletResponse response, CandidateInfoEntity candidateInfoEntity)
    {
        CandidateInfo candidateInfo = new CandidateInfo();
        candidateInfo.setCandidateName(candidateInfoEntity.getCandidateName());
        candidateInfo.setMobile(candidateInfoEntity.getMobile());
        candidateInfo.setPersonState("1");
        List<CandidateInfo> list = candidateInfoService.selectCandidateInfoList(candidateInfo);
        List<CandidateInfoEntity> candidateInfoEntityList = new ArrayList<CandidateInfoEntity>();
        if(CollectionUtil.isNotEmpty(list)){
            list.stream().forEach(m->{
                CandidateInfoEntity entity = new CandidateInfoEntity();
                entity.setCandidateName(m.getCandidateName());
                entity.setMobile(m.getMobile());
                entity.setPersonCategory(m.getPersonCategory());
                entity.setUnitName(m.getUnitName());
                entity.setPersonState(m.getPersonState());
                candidateInfoEntityList.add(entity);
            });
        }
        ExcelUtil<CandidateInfoEntity> util = new ExcelUtil<CandidateInfoEntity>(CandidateInfoEntity.class);
        util.exportExcel(response, candidateInfoEntityList, "考生数据");
    }

    /**
     * 审核考生信息
     * @param candidateInfo
     * @return
     */
    @Log(title = "审核考生信息", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/updatePersonState")
    public AjaxResult updatePersonState(CandidateInfo candidateInfo){
        return toAjax(candidateInfoService.updatePersonState(candidateInfo));
    }

    /**
     * 考试记录
     * @param candidateInfo
     * @return
     */
    @Log(title = "考试记录")
    @GetMapping(value = "/examRecord")
    @Anonymous
    public AjaxResult examRecord(HttpServletRequest request, CandidateInfo candidateInfo){
        DataUtils.appCheck(request);
        return success(candidateInfoService.examRecord(candidateInfo));
    }

    /**
     * 试卷
     * @param paperState
     * @return
     */
    @Log(title = "试卷")
    @GetMapping(value = "/testPaper")
    @Anonymous
    public AjaxResult testPaper(HttpServletRequest request, CandidatePaperState paperState){
        DataUtils.appCheck(request);
        return success(candidateInfoService.testPaper(paperState));
    }

    /**
     * 试卷查看
     * @param candidateSignUpVo
     * @param request
     * @return
     */
    @Log(title = "试卷查看")
    @PostMapping(value = "/examPaperView")
    @Anonymous
    public AjaxResult examPaperView(@Validated @RequestBody CandidateSignUpVo candidateSignUpVo, HttpServletRequest request)
    {
        DataUtils.appCheck(request);
        return success(candidateInfoService.examPaperView(candidateSignUpVo));
    }


}

