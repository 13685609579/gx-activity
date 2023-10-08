package com.ruoyi.exam.service.impl;

import com.ruoyi.exam.domain.CandidateInfo;
import com.ruoyi.exam.mapper.CandidateInfoMapper;
import com.ruoyi.exam.service.CandidateInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 考生信息表 服务实现类
 * </p>
 *
 * @author hougq
 * @since 2023-09-28
 */
@Service
public class CandidateInfoServiceImpl extends ServiceImpl<CandidateInfoMapper, CandidateInfo> implements CandidateInfoService {

    @Autowired
    private CandidateInfoMapper candidateInfoMapper;

    /**
     * 获取考生信息列表
     * @param candidateInfo
     * @return
     */
    @Override
    public List<CandidateInfo> selectCandidateInfoList(CandidateInfo candidateInfo) {
        List<CandidateInfo> candidateInfoList = candidateInfoMapper.selectCandidateInfoList(candidateInfo);
        return candidateInfoList;
    }

    /**
     * 修改考生状态
     * @param candidateInfo
     * @return
     */
    @Override
    public int updatePersonState(CandidateInfo candidateInfo) {
        int row = candidateInfoMapper.updatePersonState(candidateInfo);
        return row;
    }
}
