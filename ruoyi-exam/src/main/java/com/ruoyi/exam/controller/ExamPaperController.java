package com.ruoyi.exam.controller;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.exam.domain.vo.CandidateSignUpVo;
import com.ruoyi.exam.service.ExamPaperService;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 司法考试-试卷表 前端控制器
 * </p>
 *
 * @author yxp
 * @since 2023-10-11
 */
@RestController
@RequestMapping("/exam-paper")
public class ExamPaperController extends BaseController {

    @Autowired
    private ExamPaperService examPaperService;

    /**
     * 考试-下一题
     * @param candidateSignUpVo
     * @return
     */
    @Log(title = "考试-下一题", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/examNextTopic")
    @Anonymous
    public AjaxResult examNextTopic(@Validated @RequestBody CandidateSignUpVo candidateSignUpVo, HttpServletRequest request){
//        DataUtils.appCheck(request);
//        candidateSignUpVo.setUpdateBy(getUsername());
        candidateSignUpVo.setUpdateBy("admin");
        AjaxResult ajaxResult = examPaperService.examNextTopic(candidateSignUpVo);
        return ajaxResult;
    }

    /**
     * 考试-提交试卷
     * @param candidateSignUpVo
     * @param request
     * @return
     */
    @Log(title = "提交试卷", businessType = BusinessType.INSERT)
    @PostMapping(value = "/submitTestPaper")
    @Anonymous
    public AjaxResult submitTestPaper(@Validated @RequestBody CandidateSignUpVo candidateSignUpVo, HttpServletRequest request)
    {
//        DataUtils.appCheck(request);
        //candidateSignUpVo.setCreateBy(getUsername());
        //candidateSignUpVo.setUpdateBy(getUsername());
        candidateSignUpVo.setCreateBy("admin");
        candidateSignUpVo.setUpdateBy("admin");
        AjaxResult ajaxResult = examPaperService.submitTestPaper(candidateSignUpVo);
        return ajaxResult;
    }


}

