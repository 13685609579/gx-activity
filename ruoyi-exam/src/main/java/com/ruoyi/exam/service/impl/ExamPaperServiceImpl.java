package com.ruoyi.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.domain.vo.CandidateSignUpVo;
import com.ruoyi.exam.domain.vo.ExamResultVo;
import com.ruoyi.exam.mapper.*;
import com.ruoyi.exam.service.ExamPaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.exam.util.DataUtils;
import com.ruoyi.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 考试-下一题
     * @param candidateSignUpVo
     * @return
     */
    @Override
    @Transactional
    public AjaxResult examNextTopic(CandidateSignUpVo candidateSignUpVo) {
        AjaxResult ajaxResult = new AjaxResult();
        CandidatePaperState paperState = candidatePaperStateMapper.selectById(candidateSignUpVo.getPaperStateId());
        paperState.setRemainTime(candidateSignUpVo.getRemainTime());
        paperState.setUpdateBy(candidateSignUpVo.getUpdateBy());
        paperState.setUpdateTime(DateUtils.getNowDate());
        //修改考生试卷状态表考试剩余时间
        int row = candidatePaperStateMapper.updateById(paperState);
        boolean overTimeStatus = overTimeStatus(paperState.getStartTime());
        //点下一题校验考试是否超时
        if(!overTimeStatus){ //考试未超时
            handlePaper(candidateSignUpVo, row);
            ajaxResult = new AjaxResult(200, "请继续答题！");
        }else{ //考试超时
            ajaxResult = new AjaxResult(500, "当前考试已超时，请提交试卷！");
        }
        return ajaxResult;
    }

    /**
     * 提交试卷
     * @param candidateSignUpVo
     * @return
     */
    @Override
    @Transactional
    public AjaxResult submitTestPaper(CandidateSignUpVo candidateSignUpVo) {
        AjaxResult ajaxResult = new AjaxResult();
        CandidatePaperState paperState = candidatePaperStateMapper.selectById(candidateSignUpVo.getPaperStateId());
        int row = 0;
        if(1 == candidateSignUpVo.getSource()){
            paperState.setPaperState("0");
            paperState.setUpdateBy(candidateSignUpVo.getUpdateBy());
            paperState.setUpdateTime(DateUtils.getNowDate());
            row = candidatePaperStateMapper.updateById(paperState);
        }
        int code = 200;
        String msg = "";
        boolean overTimeStatus = overTimeStatus(paperState.getStartTime());
        if(!overTimeStatus){
            handlePaper(candidateSignUpVo, row);
            msg = "试卷提交成功！";
            if(2 == candidateSignUpVo.getSource()){
                msg = "获取答题卡数据成功！";
            }
        }else{
            code = 500;
            msg = "考试已超时！";
        }
        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setCandidateId(candidateSignUpVo.getCandidateId());
        examQuestion.setExamId(candidateSignUpVo.getExamId());
        examQuestion.setPaperStateId(Long.valueOf(candidateSignUpVo.getPaperStateId()));
        examQuestion.setTopicSort(candidateSignUpVo.getTopicSort());
        LambdaQueryWrapper<ExamQuestion> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ExamQuestion::getCandidateId, candidateSignUpVo.getCandidateId())
                .eq(ExamQuestion::getExamId, candidateSignUpVo.getExamId())
                .eq(ExamQuestion::getPaperStateId, candidateSignUpVo.getPaperStateId())
                .eq(ExamQuestion::getTopicSort, candidateSignUpVo.getTopicSort())
                .eq(ExamQuestion::getDelFlag, 0);
        List<ExamQuestion> examQuestionList = examQuestionMapper.selectList(wrapper);
        List<ExamQuestion> correctList = new ArrayList<>();
        List<ExamQuestion> errorList = new ArrayList<>();
        Integer correctScore = 0;
        if(null != examQuestionList && examQuestionList.size()>0){
            for(int i=0; i<examQuestionList.size(); i++){
                ExamQuestion question = examQuestionList.get(i);
                if(StringUtils.equals("1", question.getAnswerResult())){
                    correctList.add(question);
                    QuestionBankManage bankInfo = questionBankManageMapper.questionBankInfo(question.getTopicId());
                    if(StringUtils.equals("1", bankInfo.getTopicType()) || StringUtils.equals("3", bankInfo.getTopicType())){
                        correctScore += 2;
                    }
                    if(StringUtils.equals("2", bankInfo.getTopicType())){
                        correctScore += 5;
                    }
                }
                if(StringUtils.equals("0", question.getAnswerResult())){
                    errorList.add(question);
                }
            }
        }

        PersonClassHour personClassHour = new PersonClassHour();
        personClassHour.setCandidateId(candidateSignUpVo.getCandidateId());
        personClassHour.setExamId(candidateSignUpVo.getExamId());
        personClassHour.setTopicSort(candidateSignUpVo.getTopicSort());
        Integer classHours = personClassHourMapper.getCandidateClassHours(personClassHour);
        if(null == classHours || classHours<8){
            //新增考生考试结果
            PersonClassHour entity = new PersonClassHour();
            entity.setCandidateId(candidateSignUpVo.getCandidateId());
            entity.setThisScore(String.valueOf(correctScore));
            entity.setCorrectNum(String.valueOf(correctList.size()));
            entity.setErrorNum(String.valueOf(errorList.size()));
            entity.setClassHour("2");
            entity.setExamId(candidateSignUpVo.getExamId());
            entity.setTopicSort(candidateSignUpVo.getTopicSort());
            entity.setDelFlag("0");
            entity.setCreateTime(DateUtils.getNowDate());
            entity.setCreateBy(candidateSignUpVo.getUpdateBy());
            personClassHourMapper.insert(entity);
        }

        //考生考试结果
        LambdaQueryWrapper<ExamPaper> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(ExamPaper::getCandidateId, candidateSignUpVo.getCandidateId())
                .eq(ExamPaper::getExamId, candidateSignUpVo.getExamId())
                .eq(ExamPaper::getPaperStateId, candidateSignUpVo.getPaperStateId())
                .eq(ExamPaper::getTopicSort, candidateSignUpVo.getTopicSort())
                .eq(ExamPaper::getDelFlag, 0);
        Integer total = examPaperMapper.selectCount(wrapper1);
        ExamResultVo examResultVo = new ExamResultVo();
        examResultVo.setThisScore(correctScore);
        double v1 = Double.parseDouble(String.valueOf(correctList.size()));
        double v2 = Double.parseDouble(String.valueOf(errorList.size()));
        double correctRate = DataUtils.division(v1, v2, 4)*100;
        examResultVo.setCorrectRate(String.valueOf(correctRate)+"%");
        examResultVo.setCorrectNum(correctList.size());
        examResultVo.setErrorNum(errorList.size());
        examResultVo.setWdNum(total-correctList.size()-errorList.size());
        examResultVo.setClassHour("+"+String.valueOf(correctScore>=60?2:0));
        List<AnswerSheetVo> asVoList = new ArrayList<>();
        List<ExamPaper> paperList = examPaperMapper.selectList(wrapper1);
        if(null != paperList && paperList.size()>0){
            for(int i=0; i<paperList.size(); i++){
                ExamPaper paper = paperList.get(i);
                AnswerSheetVo asVo = new AnswerSheetVo();
                QuestionBankManage bankInfo = questionBankManageMapper.questionBankInfo(paper.getTopicId());
                if(null != bankInfo){
                    if(null != bankInfo.getTopicType() && StringUtils.equals("1", bankInfo.getTopicType())){
                        asVo.setTopicType("单选题");
                    }
                    if(null != bankInfo.getTopicType() && StringUtils.equals("2", bankInfo.getTopicType())){
                        asVo.setTopicType("多选题");
                    }
                    if(null != bankInfo.getTopicType() && StringUtils.equals("3", bankInfo.getTopicType())){
                        asVo.setTopicType("判断题");
                        asVo.setCorrectAnswer(bankInfo.getTopicAnswer());
                    }
                    asVo.setTopicContent(bankInfo.getTopicContent());
                    if(null != bankInfo.getTopicType() && (StringUtils.equals("1", bankInfo.getTopicType()) || StringUtils.equals("2", bankInfo.getTopicType()))){
                        TopicOptions topicOptions = new TopicOptions();
                        topicOptions.setTopicId(bankInfo.getTopicId());
                        List<TopicOptions> topicOptionsList = topicOptionsMapper.selectTopicOptionsList(topicOptions);
                        String correctAnswer = "";
                        if(null != topicOptionsList && topicOptionsList.size()>0){
                            asVo.setTopicOptionsList(topicOptionsList);
                            for(int j=0; j<topicOptionsList.size(); j++){
                                TopicOptions to = topicOptionsList.get(j);
                                if(StringUtils.equals("1", to.getOptionsState())){
                                    correctAnswer += to.getTopicOptionsId()+"、";
                                }
                            }
                        }
                        if(!StringUtils.equals("", correctAnswer) && correctAnswer.length()>0){
                            correctAnswer = correctAnswer.substring(0, correctAnswer.lastIndexOf("、"));
                        }
                        asVo.setCorrectAnswer(correctAnswer);
                    }
                    if(null != bankInfo.getTopicType() && StringUtils.equals("3", bankInfo.getTopicType())){
                        asVo.setTopicOptionsList(new ArrayList<TopicOptions>());
                    }
                    asVo.setAnswerAnalysis(bankInfo.getAnswerAnalysis());
                }
                asVo.setTopicNum(paper.getTopicNum());
                asVo.setTopicTotal(paperList.size());
                LambdaQueryWrapper<ExamQuestion> wrapper2 = new LambdaQueryWrapper<>();
                wrapper2.eq(ExamQuestion::getCandidateId, paper.getCandidateId())
                        .eq(ExamQuestion::getExamId, paper.getExamId())
                        .eq(ExamQuestion::getPaperStateId, paper.getPaperStateId())
                        .eq(ExamQuestion::getTopicSort, paper.getTopicSort())
                        .eq(ExamQuestion::getTopicNum, paper.getTopicNum())
                        .eq(ExamQuestion::getDelFlag, 0);
                ExamQuestion question = examQuestionMapper.selectOne(wrapper2);
                if(null != question){
                    asVo.setCandidateAnswer(question.getCandidateAnswer());
                    asVo.setAnswerResult(question.getAnswerResult());
                }else{
                    asVo.setAnswerResult("-1");
                }
                asVoList.add(asVo);
            }
        }
        examResultVo.setAsVoList(asVoList);
        ajaxResult = new AjaxResult(code, msg, examResultVo);
        return ajaxResult;
    }

    public void handlePaper(CandidateSignUpVo candidateSignUpVo, int row){
        LambdaQueryWrapper<ExamPaper> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ExamPaper::getCandidateId, candidateSignUpVo.getCandidateId())
                .eq(ExamPaper::getExamId, candidateSignUpVo.getExamId())
                .eq(ExamPaper::getPaperStateId, candidateSignUpVo.getPaperStateId())
                .eq(ExamPaper::getTopicId, candidateSignUpVo.getTopicId())
                .eq(ExamPaper::getTopicSort, candidateSignUpVo.getTopicSort())
                .eq(ExamPaper::getTopicNum, candidateSignUpVo.getTopicNum())
                .eq(ExamPaper::getDelFlag, 0);
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
            if(StringUtils.equals("1", bankManage.getTopicType()) || StringUtils.equals("2", bankManage.getTopicType())){
                String[] answers = answer.split(",");
                for(int i=0; i<answers.length; i++){
                    TopicOptions topicOptions = topicOptionsMapper.selectById(answers[i]);
                    if(null != topicOptions && StringUtils.equals("0", topicOptions.getOptionsState())){
                        answerResult = "0";
                        break;
                    }
                }
            }
            if(StringUtils.equals("3", bankManage.getTopicType()) && StringUtils.equals(answer, bankManage.getTopicAnswer())){
                answerResult = "0";
            }
        }
        LambdaQueryWrapper<ExamQuestion> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(ExamQuestion::getCandidateId, candidateSignUpVo.getCandidateId())
                .eq(ExamQuestion::getExamId, candidateSignUpVo.getExamId())
                .eq(ExamQuestion::getTopicId, candidateSignUpVo.getTopicId())
                .eq(ExamQuestion::getPaperStateId, candidateSignUpVo.getPaperStateId())
                .eq(ExamQuestion::getTopicSort, candidateSignUpVo.getTopicSort())
                .eq(ExamQuestion::getTopicNum, candidateSignUpVo.getTopicNum())
                .eq(ExamQuestion::getDelFlag, 0);
        ExamQuestion examQuestion1 = examQuestionMapper.selectOne(wrapper1);
        if(null == examQuestion1){
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setCandidateId(candidateSignUpVo.getCandidateId());
            examQuestion.setExamId(candidateSignUpVo.getExamId());
            examQuestion.setPaperStateId(Long.valueOf(candidateSignUpVo.getPaperStateId()));
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
        }else{
            examQuestion1.setCandidateAnswer(candidateSignUpVo.getCandidateAnswer());
            examQuestion1.setAnswerResult(answerResult);
            examQuestion1.setUpdateTime(DateUtils.getNowDate());
            examQuestion1.setUpdateBy(candidateSignUpVo.getUpdateBy());
            row = examQuestionMapper.updateById(examQuestion1);
        }
    }

    /**
     * 获取数据字典剩余考试时间
     * @return
     */
    public long remainTime(){
        long remainTime = 0;
        List<SysDictData> sysDictDataList = sysDictDataMapper.selectDictDataByType("remain_time");
        if(null != sysDictDataList && sysDictDataList.size()>0){
            String dictValue = sysDictDataList.get(0).getDictValue();
            if(StringUtils.isNotEmpty(dictValue) && StringUtils.isNotBlank(dictValue)){
                remainTime = Long.valueOf(Integer.parseInt(dictValue)*60*1000);
            }
        }
        return remainTime;
    }

    /**
     * 判断当前考生考试是否超时
     * @param time
     * @return
     */
    public boolean overTimeStatus(String time){
        long remainTime = remainTime();
        long timeM = DataUtils.getTimeMillis(time);
        long tm = System.currentTimeMillis(); //获取当前毫秒数
        long distance = tm - timeM;
        boolean b = false;
        if(distance>=remainTime){
            b = true;
        }
        return b;
    }

}
