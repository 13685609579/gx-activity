package com.ruoyi.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.domain.vo.CandidateSignUpVo;
import com.ruoyi.exam.mapper.*;
import com.ruoyi.exam.service.ExamPaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.exam.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <p>
 * 司法考试-试卷表 服务实现类
 * </p>
 *
 * @author yxp
 * @since 2023-10-11
 */
@Service
public class ExamPaperServiceImpl extends ServiceImpl<ExamPaperMapper, ExamPaper> implements ExamPaperService {

    @Autowired
    private ExamPaperMapper examPaperMapper;

    @Autowired
    private CandidatePaperStateMapper candidatePaperStateMapper;

    @Autowired
    private QuestionBankManageMapper questionBankManageMapper;

    @Autowired
    private TopicOptionsMapper topicOptionsMapper;

    @Autowired
    private ExamQuestionMapper examQuestionMapper;

    @Autowired
    private PersonClassHourMapper personClassHourMapper;

    /**
     * 考试-下一题
     * @param candidateSignUpVo
     * @return
     */
    @Override
    @Transactional
    public int examNextTopic(CandidateSignUpVo candidateSignUpVo) {
        CandidatePaperState paperState = candidatePaperStateMapper.selectById(candidateSignUpVo.getPaperStateId());
        int row = 0;
        long timeM = DataUtils.getTimeMillis(paperState.getStartTime());
        long tm = System.currentTimeMillis(); //获取当前毫秒数
        long distance = tm - timeM;
        //点下一题校验考试是否超时
        if(distance>0){ //考试未超时
            paperState.setRemainTime(candidateSignUpVo.getRemainTime());
            paperState.setUpdateBy(candidateSignUpVo.getUpdateBy());
            paperState.setUpdateTime(DateUtils.getNowDate());
            //修改考生试卷状态表考试剩余时间
            row = candidatePaperStateMapper.updateById(paperState);
            handlePaper(candidateSignUpVo, row);
        }else{ //考试超时
            candidatePaperStateMapper.updateById(paperState);
            row = 0;
        }
        return row;
    }

    /**
     * 提交试卷
     * @param candidateSignUpVo
     * @return
     */
    @Override
    public AjaxResult submitTestPaper(CandidateSignUpVo candidateSignUpVo) {
        AjaxResult ajaxResult = new AjaxResult();
        CandidatePaperState paperState = candidatePaperStateMapper.selectById(candidateSignUpVo.getPaperStateId());
        paperState.setPaperState("0");
        paperState.setUpdateBy(candidateSignUpVo.getUpdateBy());
        paperState.setUpdateTime(DateUtils.getNowDate());
        int row = candidatePaperStateMapper.updateById(paperState);
        int code = 200;
        String msg = "";
        long timeM = DataUtils.getTimeMillis(paperState.getStartTime());
        long tm = System.currentTimeMillis(); //获取当前毫秒数
        long distance = tm - timeM;
        if(distance>0){
            handlePaper(candidateSignUpVo, row);
        }
        PersonClassHour personClassHour = new PersonClassHour();
        personClassHour.setCandidateId(candidateSignUpVo.getCandidateId());
        personClassHour.setExamId(candidateSignUpVo.getExamId());
        personClassHour.setTopicSort(candidateSignUpVo.getTopicSort());
        int classHours = personClassHourMapper.getCandidateClassHours(personClassHour);
        if(classHours<8){

        }
        return ajaxResult;
    }

    public void handlePaper(CandidateSignUpVo candidateSignUpVo, int row){
        ExamPaper examPaper = new ExamPaper();
        examPaper.setCandidateId(candidateSignUpVo.getCandidateId());
        examPaper.setExamId(candidateSignUpVo.getExamId());
        examPaper.setTopicSort(candidateSignUpVo.getTopicSort());
        examPaper.setTopicNum(candidateSignUpVo.getTopicNum());
        LambdaQueryWrapper<ExamPaper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ExamPaper::getCandidateId, candidateSignUpVo.getCandidateId())
                .eq(ExamPaper::getExamId, candidateSignUpVo.getExamId())
                .eq(ExamPaper::getPaperStateId, candidateSignUpVo.getPaperStateId())
                .eq(ExamPaper::getTopicId, candidateSignUpVo.getTopicId())
                .eq(ExamPaper::getTopicSort, candidateSignUpVo.getTopicSort())
                .eq(ExamPaper::getTopicNum, candidateSignUpVo.getTopicNum());
        ExamPaper examPaper1 = examPaperMapper.selectOne(wrapper);
        examPaper1.setTopicState("0");
        examPaper1.setTopicSubmitTime(DateUtils.getTime());
        examPaper1.setUpdateBy(candidateSignUpVo.getUpdateBy());
        examPaper1.setUpdateTime(DateUtils.getNowDate());
        //修改考生试卷当前题目是否已答状态及其提交时间
        row = examPaperMapper.updateById(examPaper1);

        QuestionBankManage bankManage = questionBankManageMapper.questionBankInfo(candidateSignUpVo.getTopicId());
        String answer = candidateSignUpVo.getCandidateAnswer();
        String answerResult = "1";
        if(StringUtils.isNotBlank(answer) && StringUtils.isNotEmpty(answer)){
            if(StringUtils.equals("1", bankManage.getTopicSort()) && StringUtils.equals("2", bankManage.getTopicSort())){
                String[] answers = answer.split(",");
                for(int i=0; i<answers.length; i++){
                    TopicOptions topicOptions = topicOptionsMapper.selectById(answers[i]);
                    if(null != topicOptions && StringUtils.equals("0", topicOptions.getOptionsState())){
                        answerResult = "0";
                        break;
                    }
                }
            }
            if(StringUtils.equals("3", bankManage.getTopicSort()) && StringUtils.equals(answer, bankManage.getTopicAnswer())){
                answerResult = "0";
            }
        }
        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setCandidateId(candidateSignUpVo.getCandidateId());
        examQuestion.setExamId(candidateSignUpVo.getExamId());
        examQuestion.setPaperStateId(Long.valueOf(candidateSignUpVo.getCandidateId()));
        examQuestion.setTopicSort(candidateSignUpVo.getTopicSort());
        examQuestion.setTopicId(candidateSignUpVo.getTopicId());
        examQuestion.setCandidateAnswer(candidateSignUpVo.getCandidateAnswer());
        examQuestion.setAnswerResult(answerResult);
        examQuestion.setTopicNum(candidateSignUpVo.getTopicNum());
        examQuestion.setDelFlag("0");
        examQuestion.setCreateTime(DateUtils.getNowDate());
        examQuestion.setCreateBy(candidateSignUpVo.getUpdateBy());
        //新增考题表数据
        row = examQuestionMapper.insert(examQuestion);
    }

}
