package com.ruoyi.exam.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.exam.domain.CandidatePaperState;
import com.ruoyi.exam.domain.CandidateSignUp;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.exam.domain.vo.CandidateSignUpVo;

import java.util.Map;

/**
 * <p>
 * 考生报名表 服务类
 * </p>
 *
 * @author yxp
 * @since 2023-10-09
 */
public interface CandidateSignUpService extends IService<CandidateSignUp> {

    /**
     * 添加考生报名信息
     * @param candidateSignUpVo
     * @return
     */
    public AjaxResult insertCandidateSignUpData(CandidateSignUpVo candidateSignUpVo);

    public Map<String, Object> getCandidatePaperList(CandidatePaperState candidatePaperState, CandidateSignUpVo candidateSignUpVo);

}
