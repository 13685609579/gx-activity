package com.ruoyi.exam.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.exam.service.LawExamService;
import com.ruoyi.exam.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    @Anonymous
    public AjaxResult getFinalExam(HttpServletRequest request, String openId){
        DataUtils.appCheck(request);
        return success(lawExamService.getFinalExam(openId));
    }
}
