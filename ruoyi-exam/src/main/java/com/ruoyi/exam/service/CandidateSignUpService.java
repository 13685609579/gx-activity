package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.CandidateSignUp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 考生报名表 服务类
 * </p>
 *
 * @author hougq
 * @since 2023-10-09
 */
public interface CandidateSignUpService extends IService<CandidateSignUp> {

    /**
     * 添加考生报名信息
     * @param candidateSignUp
     * @return
     */
    public int insertCandidateSignUpData(CandidateSignUp candidateSignUp);

}
