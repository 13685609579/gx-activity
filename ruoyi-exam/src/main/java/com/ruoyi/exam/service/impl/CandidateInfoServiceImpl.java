package com.ruoyi.exam.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.*;
import com.ruoyi.exam.domain.vo.ExamManageVo;
import com.ruoyi.exam.domain.vo.PersonClassHourVo;
import com.ruoyi.exam.mapper.*;
import com.ruoyi.exam.service.CandidateInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.mapper.SysDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private ExamManageMapper examManageMapper;

    @Autowired
    private ClassHourSfMapper classHourSfMapper;

    @Autowired
    private PersonClassHourMapper personClassHourMapper;

    @Autowired
    private CandidateSignUpMapper candidateSignUpMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private CandidatePaperStateMapper candidatePaperStateMapper;

    /**
     * 当前考生信息
     * @param openId
     * @return
     */
    @Override
    public CandidateInfo getCandidateInfo(String openId) {
        CandidateInfo candidateInfo = new CandidateInfo();
        candidateInfo.setCandidateId(openId);
        candidateInfo = candidateInfoMapper.selectCandidateInfo(candidateInfo);
        if(null != candidateInfo){
            SysDept sysDept = sysDeptMapper.selectDeptById(Long.valueOf(candidateInfo.getUnitId()));
            if(null != sysDept){
                candidateInfo.setUnitName(sysDept.getDeptName());
            }
        }
        return candidateInfo;
    }

    /**
     * 校验考生信息
     * @param openId
     * @return
     */
    @Override
    public AjaxResult verifyCandidateInfo(String openId){
        AjaxResult ajaxResult = new AjaxResult();
        int code = -1;
        String msg = "";
        CandidateInfo candidateInfo = new CandidateInfo();
        candidateInfo.setCandidateId(openId);
        candidateInfo = candidateInfoMapper.selectCandidateInfo(candidateInfo);
        if(null == candidateInfo){
            msg = "当前用户未注册，请注册！";
        }
        if(null != candidateInfo){
            candidateInfo.setOpenId(openId);
            String personState = candidateInfo.getPersonState();
            if(StringUtils.equals("2", personState)){
                msg = "待管理员审核，请审核通过后再尝试!";
            }
            if(StringUtils.equals("1", personState)){
                msg = "该用户已注册，管理员审核通过，可直接进入首页!";
            }
            if(StringUtils.equals("0", personState)){
                msg = "该用户未被管理员审核通过，请重新修改该用户信息!";
            }
            code = Integer.parseInt(personState);
        }
        ajaxResult = new AjaxResult(code, msg, candidateInfo);
        return ajaxResult;
    }

    /**
     * 首页重要信息
     * @param openId
     * @return
     */
    @Override
    public ExamManageVo importantInformation(String openId) {
        ExamManageVo examManageVo = new ExamManageVo();
        CandidateSignUp candidateSignUp = new CandidateSignUp();
        candidateSignUp.setCandidateId(openId);
        candidateSignUp = candidateSignUpMapper.latestCandidateSignUp(candidateSignUp);
        ExamManage examManage = new ExamManage();
        if(null != candidateSignUp){
            examManage = examManageMapper.examManageInfo(candidateSignUp.getExamId());
        }else{
            examManage = examManageMapper.latestExamManageInfo();
        }
        if(null != examManage && StringUtils.equals("1", examManage.getPublishState())){
            CandidateInfo candidateInfo = new CandidateInfo();
            candidateInfo.setCandidateId(openId);
            candidateInfo = candidateInfoMapper.selectCandidateInfo(candidateInfo);
            ClassHourSf classHourSf = new ClassHourSf();
            classHourSf.setExamId(examManage.getExamId());
            classHourSf.setPersonType(candidateInfo.getPersonCategory());
            String targetHours = classHourSfMapper.getTargetHours(classHourSf);
            if(StringUtils.isNotEmpty(targetHours) && StringUtils.isNotBlank(targetHours)){
                if(targetHours.contains(".")){
                    targetHours = targetHours.substring(0, targetHours.indexOf("."));
                }
            }else{
                targetHours = "0";
            }
            PersonClassHour personClassHour = new PersonClassHour();
            personClassHour.setCandidateId(openId);
            personClassHour.setExamId(examManage.getExamId());
            String acquiredHours = personClassHourMapper.getAcquiredHours(personClassHour);
            if(StringUtils.isNotEmpty(acquiredHours) && StringUtils.isNotBlank(acquiredHours)){
                if(acquiredHours.contains(".")){
                    acquiredHours = acquiredHours.substring(0, acquiredHours.indexOf("."));
                }
            }else{
                acquiredHours = "0";
            }

            examManageVo.setExamId(examManage.getExamId());
            examManageVo.setExamTitle(examManage.getExamTitle());
            examManageVo.setStartTime(examManage.getStartTime());
            examManageVo.setEndTime(examManage.getEndTime());
            examManageVo.setExamYear(examManage.getExamYear());
            examManageVo.setTargetHours(targetHours);
            examManageVo.setAcquiredHours(acquiredHours);
        }
        return examManageVo;
    }

    /**
     * 修改考试信息
     * @param candidateInfo
     * @return
     */
    @Override
    public int updateCandidateInfo(CandidateInfo candidateInfo) {
        candidateInfo.setCandidateId(candidateInfo.getOpenId());
        candidateInfo.setPersonState("2");
        int row = candidateInfoMapper.updateCandidateInfo(candidateInfo);
        return row;
    }

    /**
     * 注册考生信息
     * @param candidateInfo
     * @return
     */
    @Override
    public int signInCandidateInfo(CandidateInfo candidateInfo) {
        candidateInfo.setCandidateId(candidateInfo.getOpenId());
        int row = candidateInfoMapper.insertCandidateInfoData(candidateInfo);
        return row;
    }

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
        int row = candidateInfoMapper.updateCandidateInfo(candidateInfo);
        return row;
    }

    /**
     * 考试记录
     * @param cInfo
     * @return
     */
    @Override
    public List<ExamManageVo> examRecord(CandidateInfo cInfo) {
        CandidatePaperState candidatePaperState = new CandidatePaperState();
        candidatePaperState.setCandidateId(cInfo.getCandidateId());
        List<CandidatePaperState> paperStateList = candidatePaperStateMapper.listCandidatePaperState(candidatePaperState);
        List<ExamManageVo> voList = new ArrayList<ExamManageVo>();
        if(null != paperStateList && paperStateList.size()>0){
            for(int i=0; i<paperStateList.size(); i++){
                ExamManageVo manageVo = new ExamManageVo();
                CandidatePaperState paperState = paperStateList.get(i);
                ExamManage examManage = examManageMapper.examManageInfo(paperState.getExamId());
                manageVo.setExamId(examManage.getExamId());
                manageVo.setExamTitle(examManage.getExamTitle());
                manageVo.setPaperState(paperState.getPaperState());
                manageVo.setStartTime(examManage.getStartTime());
                manageVo.setEndTime(examManage.getEndTime());
                manageVo.setExamYear(examManage.getExamYear());
                CandidateInfo candidateInfo = new CandidateInfo();
                candidateInfo.setCandidateId(cInfo.getCandidateId());
                candidateInfo = candidateInfoMapper.selectCandidateInfo(candidateInfo);
                ClassHourSf classHourSf = new ClassHourSf();
                classHourSf.setExamId(examManage.getExamId());
                classHourSf.setPersonType(candidateInfo.getPersonCategory());
                PersonClassHourVo personClassHourVo = classHourSfMapper.getPersonTargetHour(classHourSf);
                if(null != personClassHourVo){
                    manageVo.setTargetHours(personClassHourVo.getTargetHours());
                }
                PersonClassHour personClassHour = new PersonClassHour();
                personClassHour.setCandidateId(cInfo.getCandidateId());
                personClassHour.setExamId(paperState.getExamId());
                String acquiredHours = personClassHourMapper.getAcquiredHours(personClassHour);
                if(acquiredHours.contains(".")){
                    acquiredHours = acquiredHours.substring(0, acquiredHours.indexOf("."));
                }
                manageVo.setAcquiredHours(acquiredHours);
                voList.add(manageVo);
            }
        }
        return voList;
    }
}
