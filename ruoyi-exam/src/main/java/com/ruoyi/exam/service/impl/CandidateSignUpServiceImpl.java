package com.ruoyi.exam.service.impl;

import com.ruoyi.exam.domain.CandidateInfo;
import com.ruoyi.exam.domain.CandidateSignUp;
import com.ruoyi.exam.mapper.CandidateInfoMapper;
import com.ruoyi.exam.mapper.CandidateSignUpMapper;
import com.ruoyi.exam.service.CandidateSignUpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.exam.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 考生报名表 服务实现类
 * </p>
 *
 * @author hougq
 * @since 2023-10-09
 */
@Service
public class CandidateSignUpServiceImpl extends ServiceImpl<CandidateSignUpMapper, CandidateSignUp> implements CandidateSignUpService {

    @Autowired
    private CandidateSignUpMapper candidateSignUpMapper;

    @Autowired
    private CandidateInfoMapper candidateInfoMapper;

    /**
     * 添加考生报名信息
     * @param candidateSignUp
     * @return
     */
    @Override
    public int insertCandidateSignUpData(CandidateSignUp candidateSignUp) {
        candidateSignUp.setSignUpId(DataUtils.uuids());
        CandidateInfo candidateInfo = new CandidateInfo();
        candidateInfo.setOpenId(candidateSignUp.getCandidateId());
        candidateInfo = candidateInfoMapper.selectCandidateInfo(candidateInfo);
        if(null != candidateInfo){
            candidateSignUp.setPersonCategory(candidateInfo.getPersonCategory());
        }
        int row = candidateSignUpMapper.insertCandidateSignUpData(candidateSignUp);
        return row;
    }
}
