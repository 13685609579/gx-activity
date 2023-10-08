package com.ruoyi.exam.controller;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.exam.domain.ExamManage;
import com.ruoyi.exam.service.ExamManageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 考试管理表 前端控制器
 * </p>
 *
 * @author hougq
 * @since 2023-09-28
 */
@RestController
@RequestMapping("/exam-manage")
public class ExamManageController extends BaseController {

    @Autowired
    private ExamManageService examManageService;

    /**
     * 获取考试管理列表
     * @param examManage
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo list(ExamManage examManage)
    {
        startPage();
        List<ExamManage> list = examManageService.selectExamManageList(examManage);
        return getDataTable(list);
    }

    /**
     * 新增题库题目
     * @param examManage
     * @return
     */
    @Log(title = "新增题库题目", businessType = BusinessType.INSERT)
    @PostMapping(value = "/insertExamManageData")
    public AjaxResult insertExamManageData(@Validated @RequestBody ExamManage examManage)
    {
//        questionBankManage.setCreateBy(getUsername());
        examManage.setCreateBy("admin");
        return toAjax(examManageService.insertExamManageData(examManage));
    }

    /**
     * 获取考试项目详情
     * @param examId
     * @return
     */
    @GetMapping(value = "/examManageInfo")
    public AjaxResult examManageInfo(String examId){
        return success(examManageService.examManageInfo(examId));
    }

}

