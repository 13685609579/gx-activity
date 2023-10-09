package com.ruoyi.exam.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.exam.service.LawExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 考试管理表 前端控制器
 * </p>
 *
 * @author yxp
 * @since 2023-10-09
 */
@RestController
@RequestMapping("/law-exam")
public class LawExamController extends BaseController {

    @Autowired
    private LawExamService lawExamService;

    /**
     * 考试（结题考试）
     * @param openId
     * @return
     */
    @GetMapping(value = "/getFinalExam")
    public AjaxResult getFinalExam(String openId){
        return success(lawExamService.getFinalExam(openId));
    }
}
