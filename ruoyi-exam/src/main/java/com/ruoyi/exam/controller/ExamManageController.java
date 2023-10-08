package com.ruoyi.exam.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class ExamManageController extends BaseController{

    @Autowired
    private ExamManageService examManageService;

    /**
     * 获取考试管理列表
     * @param questionBankManage
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo list(ExamManage examManage)
    {
        startPage();
        List<ExamManage> list = examManageService.selectExamManageList(examManage);
        return getDataTable(list);
    }

    @Log(title = "新增题库题目", businessType = BusinessType.INSERT)
    @PostMapping(value = "/insertExamManageData")
    public AjaxResult insertExamManageData(@Validated @RequestBody ExamManage examManage)
    {
//        questionBankManage.setCreateBy(getUsername());
        questionBankManage.setCreateBy("admin");
        return toAjax(examManageService.insertExamManageData(examManage));
    }

}

