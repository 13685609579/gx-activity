package com.ruoyi.exam.controller;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.QuestionBank;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.exam.domain.QuestionBankManage;
import com.ruoyi.exam.service.QuestionBankManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 题库管理表 前端控制器
 * </p>
 *
 * @author yxp
 * @since 2023-09-28
 */
@RestController
@RequestMapping("/questionBankManage")
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

    /**
     * 删除题目
     * @param topicId
     * @return
     */
    @Log(title = "删除题目", businessType = BusinessType.UPDATE)
    @GetMapping(value = "/removeQuestionBank")
    public AjaxResult removeQuestionBank(String topicId){
        return toAjax(questionBankManageService.removeQuestionBank(topicId));
    }

    /**
     * 导入>下载模板
     * @param response
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<QuestionBank> util = new ExcelUtil<QuestionBank>(QuestionBank.class);
        util.importTemplateExcel(response, "题目数据");
    }

    /**
     * 导入题库
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */
    @Log(title = "导入题库", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<QuestionBank> util = new ExcelUtil<QuestionBank>(QuestionBank.class);
        List<QuestionBank> userList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = questionBankManageService.importQuestionBank(userList, updateSupport, operName);
        return success(message);
    }

}

