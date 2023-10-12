package com.ruoyi.exam.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.domain.vo.CandidateSignUpVo;
import com.ruoyi.exam.mapper.CandidateInfoMapper;
import com.ruoyi.exam.mapper.CandidateSignUpMapper;
import com.ruoyi.exam.mapper.QuestionBankManageMapper;
import com.ruoyi.exam.mapper.TopicOptionsMapper;
import com.ruoyi.exam.service.CandidateSignUpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.exam.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 考生报名表 服务实现类
 * </p>
 *
 * @author yxp
 * @since 2023-10-09
 */
@Service
public class CandidateSignUpServiceImpl extends ServiceImpl<CandidateSignUpMapper, CandidateSignUp> implements CandidateSignUpService {

    @Autowired
    private CandidateSignUpMapper candidateSignUpMapper;

    @Autowired
    private CandidateInfoMapper candidateInfoMapper;

    @Autowired
    private QuestionBankManageMapper questionBankManageMapper;

    @Autowired
    private TopicOptionsMapper topicOptionsMapper;

    /**
     * 添加考生报名信息
     * @param candidateSignUp
     * @return
     */
    @Override
    public AjaxResult insertCandidateSignUpData(CandidateSignUpVo candidateSignUpVo) {
        AjaxResult ajaxResult = new AjaxResult();
        CandidateSignUp candidateSignUp = new CandidateSignUp();
        String signUpId = DataUtils.uuids();
        candidateSignUp.setSignUpId(signUpId);
        CandidateInfo candidateInfo = new CandidateInfo();
        candidateInfo.setCandidateId(candidateSignUpVo.getCandidateId());
        candidateInfo.setCandidateName(candidateSignUpVo.getCandidateName());
        candidateInfo.setUnitId(candidateSignUpVo.getUnitId());
        candidateInfo = candidateInfoMapper.selectCandidateInfo(candidateInfo);
        if(null != candidateInfo){
            candidateSignUp.setPersonCategory(candidateInfo.getPersonCategory());
        }
        candidateSignUp.setCreateBy(candidateSignUpVo.getCreateBy());
        candidateSignUp.setCandidateId(candidateSignUpVo.getCandidateId());
        candidateSignUp.setCandidateName(candidateSignUpVo.getCandidateName());
        candidateSignUp.setUnitId(candidateSignUpVo.getUnitId());
        candidateSignUp.setExamId(candidateSignUpVo.getExamId());
        int row = candidateSignUpMapper.insertCandidateSignUpData(candidateSignUp);
        int code = 200;
        String msg = "";
        List<QuestionBankManage> list = new ArrayList<>();
        if(1 == row){
            QuestionBankManage questionBankManage = new QuestionBankManage();
            questionBankManage.setTopicSort(candidateSignUpVo.getTopicSort());
            list = questionBankManageMapper.selectExamQuestion(questionBankManage);
            if(null != list && list.size()>0){
                list.stream().forEach(m->{
                    TopicOptions topicOptions = new TopicOptions();
                    topicOptions.setTopicId(m.getTopicId());
                    List<TopicOptions> topicOptionsList = topicOptionsMapper.selectTopicOptionsList(topicOptions);
                    m.setTopicOptionsList(topicOptionsList);
                });
            }
            msg = "考生信息正确，可直接参与考试！";
        }else{
            code = 500;
            msg = "考生信息确认有误，请重新填写考生信息！";
        }
        ajaxResult = new AjaxResult(code, msg, list);
        return ajaxResult;
    }
}
