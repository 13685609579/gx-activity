package com.ruoyi.exam.controller;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.exam.domain.TestTrain;
import com.ruoyi.exam.service.TestTrainService;
import com.ruoyi.exam.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 测试训练表 前端控制器
 * </p>
 *
 * @author yxp
 * @since 2023-10-13
 */
@RestController
@RequestMapping("/test-train")
public class TestTrainController extends BaseController {

    @Autowired
    private TestTrainService testTrainService;

    /**
     * 测试训练
     * @param candidateId 考生ID
     * @param dictType 字典类型（题目分类参数值topic_sort）
     * @param request
     * @return
     */
    @GetMapping(value = "/topicSortList")
    @Anonymous
    public AjaxResult topicSortList(String candidateId, String dictType, HttpServletRequest request)
    {
        DataUtils.appCheck(request);
        return success(testTrainService.topicSortList(candidateId, dictType));
    }

    /**
     * 测试训练-继续上次
     * @param testTrain
     * @param request
     * @return
     */
    @PostMapping(value = "/continueLastTime")
    @Anonymous
    public AjaxResult continueLastTime(@Validated @RequestBody TestTrain testTrain, HttpServletRequest request)
    {
        DataUtils.appCheck(request);
        AjaxResult ajaxResult = testTrainService.continueLastTime(testTrain);
        return ajaxResult;
    }

    /**
     * 测试-结束答题
     * @param testTrain
     * @param request
     * @return
     */
    @Log(title = "测试-结束答题", businessType = BusinessType.INSERT)
    @PostMapping(value = "/endAnswer")
    @Anonymous
    public AjaxResult endAnswer(@Validated @RequestBody TestTrain testTrain, HttpServletRequest request)
    {
        DataUtils.appCheck(request);
        return success(testTrainService.endAnswer(testTrain));
    }

    /**
     * 测试-提交
     * @param testTrain
     * @param request
     * @return
     */
    @Log(title = "测试-提交", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/submitTest")
    @Anonymous
    public AjaxResult submitTest(@Validated @RequestBody TestTrain testTrain, HttpServletRequest request)
    {
        DataUtils.appCheck(request);
        AjaxResult ajaxResult = testTrainService.submitTest(testTrain);
        return ajaxResult;
    }

    /**
     * 测试训练-重新开始
     * @param testTrain
     * @param request
     * @return
     */
    @PostMapping(value = "/restart")
    @Anonymous
    public AjaxResult restart(@Validated @RequestBody TestTrain testTrain, HttpServletRequest request)
    {
        DataUtils.appCheck(request);
        return success(testTrainService.restart(testTrain));
    }

    /**
     * 测试训练-下一题
     * @param testTrain
     * @param request
     * @return
     */
    @PostMapping(value = "/nextTestTopic")
    @Anonymous
    public AjaxResult nextTestTopic(@Validated @RequestBody TestTrain testTrain, HttpServletRequest request)
    {
        DataUtils.appCheck(request);
        AjaxResult ajaxResult = testTrainService.nextTestTopic(testTrain);
        return ajaxResult;
    }




}

