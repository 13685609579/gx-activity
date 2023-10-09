package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.CandidateInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 考生信息表 Mapper 接口
 * </p>
 *
 * @author yxp
 * @since 2023-09-28
 */
public interface CandidateInfoMapper extends BaseMapper<CandidateInfo> {

    /**
     * 获取当前考生信息
     * @param candidateInfo
     * @return
     */
    public CandidateInfo selectCandidateInfo(CandidateInfo candidateInfo);

    /**
     * 注册考生信息
     * @param candidateInfo
     * @return
     */
    public int insertCandidateInfoData(CandidateInfo candidateInfo);

    /**
     * 获取考生信息列表
     * @param candidateInfo
     * @return
     */
    public List<CandidateInfo> selectCandidateInfoList(CandidateInfo candidateInfo);

    /**
     * 修改考生状态
     * @param candidateInfo
     * @return
     */
    public int updatePersonState(CandidateInfo candidateInfo);

}
