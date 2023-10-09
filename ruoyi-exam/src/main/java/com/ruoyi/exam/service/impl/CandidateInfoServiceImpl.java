package com.ruoyi.exam.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.CandidateInfo;
import com.ruoyi.exam.domain.ClassHourSf;
import com.ruoyi.exam.domain.ExamManage;
import com.ruoyi.exam.domain.PersonClassHour;
import com.ruoyi.exam.domain.vo.ExamManageVo;
import com.ruoyi.exam.mapper.CandidateInfoMapper;
import com.ruoyi.exam.mapper.ClassHourSfMapper;
import com.ruoyi.exam.mapper.ExamManageMapper;
import com.ruoyi.exam.mapper.PersonClassHourMapper;
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

    @Autowired
    private ExamManageMapper examManageMapper;

    @Autowired
    private ClassHourSfMapper classHourSfMapper;

    @Autowired
    private PersonClassHourMapper personClassHourMapper;

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
        candidateInfo.setOpenId(openId);
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
        ExamManage examManage = examManageMapper.latestExamManageInfo();
        if(null != examManage && StringUtils.equals("1", examManage.getPublishState())){
            CandidateInfo candidateInfo = new CandidateInfo();
            candidateInfo.setOpenId(openId);
            candidateInfo = candidateInfoMapper.selectCandidateInfo(candidateInfo);
            ClassHourSf classHourSf = new ClassHourSf();
            classHourSf.setExamId(examManage.getExamId());
            classHourSf.setPersonType(candidateInfo.getPersonCategory());
            String targetHours = classHourSfMapper.getTargetHours(classHourSf);
            if(StringUtils.isNotEmpty(targetHours) && StringUtils.isNotBlank(targetHours)){
                targetHours = targetHours.substring(0, targetHours.indexOf("."));
            }
            PersonClassHour personClassHour = new PersonClassHour();
            personClassHour.setCandidateId(openId);
            personClassHour.setExamYear(examManage.getExamYear());
            personClassHour.setStartTime(examManage.getStartTime());
            personClassHour.setEndTime(examManage.getEndTime());
            String acquiredHours = personClassHourMapper.getAcquiredHours(personClassHour);
            if(StringUtils.isNotEmpty(acquiredHours) && StringUtils.isNotBlank(acquiredHours)){
                acquiredHours = acquiredHours.substring(0, acquiredHours.indexOf("."));
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
        int row = candidateInfoMapper.updatePersonState(candidateInfo);
        return row;
    }
}
