package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.CandidateSignUp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 考生报名表 Mapper 接口
 * </p>
 *
 * @author yxp
 * @since 2023-10-09
 */
public interface CandidateSignUpMapper extends BaseMapper<CandidateSignUp> {

    /**
     * 添加考生报名信息
     * @param candidateSignUp
     * @return
     */
    public int insertCandidateSignUpData(CandidateSignUp candidateSignUp);

    /**
     * 获取考生最新报名信息
     * @param candidateSignUp
     * @return
     */
    public CandidateSignUp latestCandidateSignUp(CandidateSignUp candidateSignUp);

}
