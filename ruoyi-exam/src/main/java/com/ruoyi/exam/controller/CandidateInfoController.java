package com.ruoyi.exam.controller;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.exam.domain.CandidateInfo;
import com.ruoyi.exam.domain.ExamManage;
import com.ruoyi.exam.service.CandidateInfoService;
import com.ruoyi.exam.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 考生信息表 前端控制器
 * </p>
 *
 * @author hougq
 * @since 2023-09-28
 */
@RestController
@RequestMapping("/candidate-info")
public class CandidateInfoController extends BaseController {

    @Autowired
    private CandidateInfoService candidateInfoService;

    /**
     * 校验考生信息
     * @param openId
     * @return
     */
    @GetMapping("/verify")
    @Anonymous
    public AjaxResult verifyCandidateInfo(HttpServletRequest request, String openId){
//        DataUtils.appCheck(request);
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
        //        questionBankManage.setCreateBy(getUsername());
        candidateInfo.setCreateBy("admin");
        return toAjax(candidateInfoService.signInCandidateInfo(candidateInfo));
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
     * 修改考生信息
     * @param candidateInfo
     * @return
     */
    @Log(title = "修改考生信息", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/updateCandidateInfo")
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
     * 审核考生信息
     * @param candidateInfo
     * @return
     */
    @Log(title = "审核考生信息", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/updatePersonState")
    public AjaxResult updatePersonState(CandidateInfo candidateInfo){
        return toAjax(candidateInfoService.updatePersonState(candidateInfo));
    }

}

