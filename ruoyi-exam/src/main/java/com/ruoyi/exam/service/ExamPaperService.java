package com.ruoyi.exam.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.exam.domain.ExamPaper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.exam.domain.vo.CandidateSignUpVo;

/**
 * <p>
 * 司法考试-试卷表 服务类
 * </p>
 *
 * @author yxp
 * @since 2023-10-11
 */
public interface ExamPaperService extends IService<ExamPaper> {

    /**
     * 考试-下一题
     * @param candidateSignUpVo
     * @return
     */
    public AjaxResult examNextTopic(CandidateSignUpVo candidateSignUpVo);

    /**
     * 提交试卷
     * @param candidateSignUpVo
     * @return
     */
    public AjaxResult submitTestPaper(CandidateSignUpVo candidateSignUpVo);

}
