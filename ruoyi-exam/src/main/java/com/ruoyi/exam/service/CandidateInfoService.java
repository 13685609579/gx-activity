package com.ruoyi.exam.service;

import com.ruoyi.exam.domain.CandidateInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 考生信息表 服务类
 * </p>
 *
 * @author hougq
 * @since 2023-09-28
 */
public interface CandidateInfoService extends IService<CandidateInfo> {

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
