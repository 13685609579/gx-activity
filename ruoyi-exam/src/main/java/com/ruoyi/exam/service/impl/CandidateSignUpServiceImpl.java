package com.ruoyi.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.domain.vo.CandidateSignUpVo;
import com.ruoyi.exam.mapper.*;
import com.ruoyi.exam.service.CandidateSignUpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.exam.util.DataUtils;
import com.ruoyi.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private CandidatePaperStateMapper candidatePaperStateMapper;

    @Autowired
    private ExamPaperMapper examPaperMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private ExamQuestionMapper examQuestionMapper;

    /**
     * 添加考生报名信息
     * @param candidateSignUpVo
     * @return
     */
    @Override
    @Transactional
    public AjaxResult insertCandidateSignUpData(CandidateSignUpVo candidateSignUpVo) {
        AjaxResult ajaxResult = new AjaxResult();
        int code = 200;
        String msg = "";
        CandidateSignUp signUp = new CandidateSignUp();
        signUp.setCandidateId(candidateSignUpVo.getCandidateId());
        signUp.setCandidateName(candidateSignUpVo.getCandidateName());
        signUp.setUnitId(candidateSignUpVo.getUnitId());
        signUp.setExamId(candidateSignUpVo.getExamId());
        signUp.setTopicSort(candidateSignUpVo.getTopicSort());
        signUp = candidateSignUpMapper.latestCandidateSignUp(signUp);
        Map<String, Object> examPaperData = new HashMap<>();
        if(null == signUp){
            CandidateInfo candidateInfo = new CandidateInfo();
            candidateInfo.setCandidateId(candidateSignUpVo.getCandidateId());
            candidateInfo.setCandidateName(candidateSignUpVo.getCandidateName());
            candidateInfo.setUnitId(candidateSignUpVo.getUnitId());
            candidateInfo = candidateInfoMapper.selectCandidateInfo(candidateInfo);
            if(null != candidateInfo){
                CandidateSignUp candidateSignUp = new CandidateSignUp();
                String signUpId = DataUtils.uuids();
                candidateSignUp.setSignUpId(signUpId);
                candidateSignUp.setPersonCategory(candidateInfo.getPersonCategory());
                candidateSignUp.setCreateBy(candidateSignUpVo.getCreateBy());
                candidateSignUp.setCandidateId(candidateSignUpVo.getCandidateId());
                candidateSignUp.setCandidateName(candidateSignUpVo.getCandidateName());
                candidateSignUp.setUnitId(candidateSignUpVo.getUnitId());
                candidateSignUp.setExamId(candidateSignUpVo.getExamId());
                candidateSignUp.setTopicSort(candidateSignUpVo.getTopicSort());
                int row = candidateSignUpMapper.insertCandidateSignUpData(candidateSignUp);
                if(1 == row){
                    CandidatePaperState candidatePaperState = new CandidatePaperState();
                    candidatePaperState.setCandidateId(candidateSignUpVo.getCandidateId());
                    candidatePaperState.setExamId(candidateSignUpVo.getExamId());
                    candidatePaperState.setTopicSort(candidateSignUpVo.getTopicSort());
                    //获取本场考试试卷
                    examPaperData = getCandidatePaperList(candidatePaperState, candidateSignUpVo);
                    msg = "考生信息正确，可直接参与考试！";
                }else{
                    code = 500;
                    msg = "考生信息确认有误，请重新填写考生信息！";
                }
            }else{
                code = 500;
                msg = "考生信息确认有误，请重新填写考生信息！";
            }
        }else{
            CandidatePaperState candidatePaperState = new CandidatePaperState();
            candidatePaperState.setCandidateId(candidateSignUpVo.getCandidateId());
            candidatePaperState.setExamId(candidateSignUpVo.getExamId());
            candidatePaperState.setTopicSort(candidateSignUpVo.getTopicSort());
            examPaperData = getCandidatePaperList(candidatePaperState, candidateSignUpVo);
            msg = "该场考试考生已报名，可直接参与考试！";
        }
        ajaxResult = new AjaxResult(code, msg, examPaperData);
        return ajaxResult;
    }

    /**
     * 获取本场考试试卷
     * @param candidatePaperState
     * @param candidateSignUpVo
     * @return
     */
    @Override
    public Map<String, Object> getCandidatePaperList(CandidatePaperState candidatePaperState, CandidateSignUpVo candidateSignUpVo) {
        Map<String, Object> examPaperData = new HashMap<>();
        examPaperData.put("resumeExamStatus", 0); //resumeExamStatus：是否可以继续考试状态（0：继续 1：终止）
        //当前考试剩余时间
        String remainTime = "";
        //根据考生ID、考试管理ID、题目分类查看当前考生是否存在正在进行中的试卷
        LambdaQueryWrapper<CandidatePaperState> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(CandidatePaperState::getCandidateId, candidatePaperState.getCandidateId())
                .eq(CandidatePaperState::getExamId, candidatePaperState.getExamId())
                .eq(CandidatePaperState::getTopicSort, candidatePaperState.getTopicSort())
                .eq(CandidatePaperState::getPaperState, 1)
                .eq(CandidatePaperState::getDelFlag, 0);
        CandidatePaperState paperState = candidatePaperStateMapper.selectOne(queryWrapper1);
        Long paperStateId = null;
        List<QuestionBankManage> list = new ArrayList<>();
        if(null != paperState){ //当前考生存在正在进行中的试卷
            paperStateId = paperState.getId();
            //获取当前考试剩余时间
            remainTime = paperState.getRemainTime();
            //获取正在进行中的试卷的未完成的考题
            LambdaQueryWrapper<ExamPaper> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(ExamPaper::getCandidateId, paperState.getCandidateId())
                    .eq(ExamPaper::getExamId, paperState.getExamId())
                    .eq(ExamPaper::getTopicSort, candidateSignUpVo.getTopicSort())
                    .eq(ExamPaper::getPaperStateId, paperState.getId())
                    .eq(ExamPaper::getDelFlag, 0);
            List<ExamPaper> examPaperList = examPaperMapper.selectList(queryWrapper2);
            if(null != examPaperList && examPaperList.size()>0){
                for(int i = 0; i<examPaperList.size(); i++){
                    ExamPaper examPaper = examPaperList.get(i);
                    QuestionBankManage questionBankManage = questionBankManageMapper.questionBankInfo(examPaper.getTopicId());
                    getQuestionBankManage(questionBankManage);
                    questionBankManage.setTopicNum(String.valueOf(examPaper.getTopicNum()));
                    questionBankManage.setTopicState(examPaper.getTopicState());
                    if(StringUtils.equals("0", examPaper.getTopicState())){
                        LambdaQueryWrapper<ExamQuestion> wrapper = new LambdaQueryWrapper<>();
                        wrapper.eq(ExamQuestion::getCandidateId, examPaper.getCandidateId())
                                .eq(ExamQuestion::getExamId, examPaper.getExamId())
                                .eq(ExamQuestion::getTopicId, examPaper.getTopicId())
                                .eq(ExamQuestion::getPaperStateId, examPaper.getPaperStateId())
                                .eq(ExamQuestion::getTopicSort, examPaper.getTopicSort())
                                .eq(ExamQuestion::getTopicNum, examPaper.getTopicNum())
                                .eq(ExamQuestion::getDelFlag, 0);
                        ExamQuestion examQuestion = examQuestionMapper.selectOne(wrapper);
                        if(null != examQuestion){
                            questionBankManage.setCandidateAnswer(examQuestion.getCandidateAnswer());
                        }
                    }
                    list.add(questionBankManage);
                }
            }
            boolean overTimeStatus = overTimeStatus(paperState.getStartTime());
            if(!overTimeStatus){
                examPaperData.put("resumeExamStatus", 1);
            }
        }else{ //当前考生不存在正在进行中的试卷
            List<SysDictData> sysDictDataList = sysDictDataMapper.selectDictDataByType("remain_time");
            if(null != sysDictDataList && sysDictDataList.size()>0){
                remainTime = sysDictDataList.get(0).getDictValue();
                if(StringUtils.isNotEmpty(remainTime) && StringUtils.isNotBlank(remainTime)){
                    remainTime = String.valueOf(Integer.parseInt(remainTime)*60*1000);
                }
            }
            //新增考生答卷数据
            insertExamPaperStateData(candidateSignUpVo, remainTime);
            //根据考生ID、考试管理ID、题目分类查看刚新增的考生答卷数据
            LambdaQueryWrapper<CandidatePaperState> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(CandidatePaperState::getCandidateId, candidatePaperState.getCandidateId())
                    .eq(CandidatePaperState::getExamId, candidatePaperState.getExamId())
                    .eq(CandidatePaperState::getTopicSort, candidatePaperState.getTopicSort())
                    .eq(CandidatePaperState::getPaperState, 1)
                    .eq(CandidatePaperState::getDelFlag, 0);
            CandidatePaperState candidatePaperState1 = candidatePaperStateMapper.selectOne(queryWrapper2);
            paperStateId = candidatePaperState1.getId();
            QuestionBankManage questionBankManage = new QuestionBankManage();
            questionBankManage.setTopicSort(candidateSignUpVo.getTopicSort());
            list = questionBankManageMapper.selectCandidatePaper(questionBankManage);
            if(null != list && list.size()>0){
                for(int i=0; i<list.size(); i++){
                    QuestionBankManage bankManage = list.get(i);
                    bankManage.setTopicState("0");
                    getQuestionBankManage(bankManage);
                    insertExamPaperData(bankManage, candidateSignUpVo, paperStateId);
                }
            }

        }
        candidateSignUpVo.setRemainTime(remainTime);
        candidateSignUpVo.setPaperStateId(paperStateId);
        candidateSignUpVo.setTopicTotal(list.size());
        examPaperData.put("examPaperData", list);
        examPaperData.put("candidateSignUpVo", candidateSignUpVo);
        return examPaperData;
    }

    public void getQuestionBankManage(QuestionBankManage questionBankManage){
        TopicOptions topicOptions = new TopicOptions();
        topicOptions.setTopicId(questionBankManage.getTopicId());
        List<TopicOptions> topicOptionsList = topicOptionsMapper.selectTopicOptionsList(topicOptions);
        questionBankManage.setTopicOptionsList(topicOptionsList);
    }

    /**
     * 新增考生答卷表数据
     * @param candidateSignUpVo
     * @param remainTime
     */
    public void insertExamPaperStateData(CandidateSignUpVo candidateSignUpVo, String remainTime){
        CandidatePaperState candidatePaperState = new CandidatePaperState();
        candidatePaperState.setCandidateId(candidateSignUpVo.getCandidateId());
        candidatePaperState.setExamId(candidateSignUpVo.getExamId());
        candidatePaperState.setTopicSort(candidateSignUpVo.getTopicSort());
        candidatePaperState.setPaperState("1");
        candidatePaperState.setStartTime(DateUtils.getTime());
        candidatePaperState.setRemainTime(remainTime);
        candidatePaperState.setDelFlag("0");
        candidatePaperState.setCreateTime(DateUtils.getNowDate());
        candidatePaperState.setCreateBy(candidateSignUpVo.getCreateBy());
        candidatePaperStateMapper.insert(candidatePaperState);
    }

    /**
     * 新增试卷表数据
     * @param questionBankManage
     * @param candidateSignUpVo
     * @param id
     */
    public void insertExamPaperData(QuestionBankManage questionBankManage, CandidateSignUpVo candidateSignUpVo, Long id){
        ExamPaper examPaper = new ExamPaper();
        examPaper.setCandidateId(candidateSignUpVo.getCandidateId());
        examPaper.setExamId(candidateSignUpVo.getExamId());
        examPaper.setTopicSort(candidateSignUpVo.getTopicSort());
        examPaper.setPaperStateId(id);
        examPaper.setTopicId(questionBankManage.getTopicId());
        examPaper.setTopicNum(Integer.valueOf(questionBankManage.getTopicNum()));
        examPaper.setTopicState("1");
        examPaper.setDelFlag("0");
        examPaper.setCreateTime(DateUtils.getNowDate());
        examPaper.setCreateBy(candidateSignUpVo.getCreateBy());
        examPaperMapper.insert(examPaper);
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
