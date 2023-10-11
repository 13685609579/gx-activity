package com.ruoyi.exam.controller;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.exam.domain.CandidateInfo;
import com.ruoyi.exam.domain.ExamManage;
import com.ruoyi.exam.service.CandidateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public AjaxResult verifyCandidateInfo(String openId){
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
    public AjaxResult signInCandidateInfo(@Validated @RequestBody CandidateInfo candidateInfo)
    {
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
    public AjaxResult importantInformation(String openId){
        return success(candidateInfoService.importantInformation(openId));
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
     * 修改考生状态
     * @param candidateInfo
     * @return
     */
    @Log(title = "修改考生状态", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/updatePersonState")
    public AjaxResult updatePersonState(CandidateInfo candidateInfo){
        return toAjax(candidateInfoService.updatePersonState(candidateInfo));
    }

}

