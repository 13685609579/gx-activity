package com.ruoyi.exam.controller;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.QuestionBankManage;
import com.ruoyi.exam.service.QuestionBankManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 题库管理表 前端控制器
 * </p>
 *
 * @author hougq
 * @since 2023-09-28
 */
@RestController
@RequestMapping("/question-bank-manage")
public class QuestionBankManageController extends BaseController {

    @Autowired
    private QuestionBankManageService questionBankManageService;

    /**
     * 获取题库列表
     * @param questionBankManage
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo list(QuestionBankManage questionBankManage)
    {
        startPage();
        List<QuestionBankManage> list = questionBankManageService.selectQuestionBankList(questionBankManage);
        return getDataTable(list);
    }

    /**
     * 获取当前题目详情
     * @return
     */
    @GetMapping(value = "/getTopicCode")
    public AjaxResult getTopicCode(){
        return success(questionBankManageService.getTopicCode());
    }

    /**
     * 新增题库题目
     * @param questionBankManage
     * @return
     */
    @Log(title = "新增题库题目", businessType = BusinessType.INSERT)
    @PostMapping(value = "/addQuestionBank")
    public AjaxResult addQuestionBank(@Validated @RequestBody QuestionBankManage questionBankManage)
    {
//        questionBankManage.setCreateBy(getUsername());
        questionBankManage.setCreateBy("admin");
        return toAjax(questionBankManageService.insertQuestionBankData(questionBankManage));
    }

    /**
     * 获取当前题目详情
     * @param topicId
     * @return
     */
    @GetMapping(value = "/questionBankInfo")
    public AjaxResult questionBankInfo(String topicId){
        return success(questionBankManageService.questionBankInfo(topicId));
    }

    /**
     * 修改当前题目
     * @param questionBankManage
     * @return
     */
    @Log(title = "修改当前题目", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/updateQuestionBank")
    public AjaxResult updateQuestionBank(@Validated @RequestBody QuestionBankManage questionBankManage){
        return toAjax(questionBankManageService.updateQuestionBank(questionBankManage));
    }

}

