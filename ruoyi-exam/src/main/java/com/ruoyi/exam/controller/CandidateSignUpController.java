package com.ruoyi.exam.controller;


import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.exam.domain.CandidateInfo;
import com.ruoyi.exam.domain.CandidateSignUp;
import com.ruoyi.exam.domain.vo.CandidateSignUpVo;
import com.ruoyi.exam.service.CandidateSignUpService;
import com.ruoyi.exam.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 考生报名表 前端控制器
 * </p>
 *
 * @author yxp
 * @since 2023-10-09
 */
@RestController
@RequestMapping("/candidate-sign-up")
public class CandidateSignUpController extends BaseController {

    @Autowired
    private CandidateSignUpService candidateSignUpService;

    /**
     * 信息填写确定
     * @param candidateSignUpVo
     * @return
     */
    @Log(title = "信息填写确定", businessType = BusinessType.INSERT)
    @PostMapping(value = "/insertCandidateSignUpData")
    @Anonymous
    public AjaxResult insertCandidateSignUpData(@Validated @RequestBody CandidateSignUpVo candidateSignUpVo, HttpServletRequest request)
    {
        DataUtils.appCheck(request);
        candidateSignUpVo.setCreateBy(getUsername());
        AjaxResult ajaxResult = candidateSignUpService.insertCandidateSignUpData(candidateSignUpVo);
        return ajaxResult;
    }

}

