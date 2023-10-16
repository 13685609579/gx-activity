package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.CandidatePaperState;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 司法考试-考生试卷状态表 Mapper 接口
 * </p>
 *
 * @author yxp
 * @since 2023-10-11
 */
public interface CandidatePaperStateMapper extends BaseMapper<CandidatePaperState> {

    /**
     * 获取考生参加的所有考试
     * @param candidatePaperState
     * @return
     */
    public List<CandidatePaperState> listCandidatePaperState(CandidatePaperState candidatePaperState);

}
