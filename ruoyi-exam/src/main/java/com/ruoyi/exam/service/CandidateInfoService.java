package com.ruoyi.exam.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.exam.domain.CandidateInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.exam.domain.CandidatePaperState;
import com.ruoyi.exam.domain.ExamManage;
import com.ruoyi.exam.domain.vo.CandidateSignUpVo;
import com.ruoyi.exam.domain.vo.ExamManageVo;
import com.ruoyi.exam.domain.vo.ExamResultVo;

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
     * 当前考生信息
     * @param openId
     * @return
     */
    public CandidateInfo getCandidateInfo(String openId);

    /**
     * 校验考生信息
     * @param openId
     * @return
     */
    public AjaxResult verifyCandidateInfo(String openId);

    /**
     * 首页重要信息
     * @param openId
     * @return
     */
    public ExamManageVo importantInformation(String openId);

    /**
     * candidateInfo
     * @param candidateInfo
     * @return
     */
    public int updateCandidateInfo(CandidateInfo candidateInfo);

    /**
     * 注册考生信息
     * @param candidateInfo
     * @return
     */
    public int signInCandidateInfo(CandidateInfo candidateInfo);

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

    /**
     * 考试记录
     * @param cInfo
     * @return
     */
    public List<ExamManageVo> examRecord(CandidateInfo cInfo);

    /**
     * 试卷
     * @param cInfo
     * @return
     */
    public List<ExamManageVo> testPaper(CandidatePaperState cInfo);

    /**
     * 试卷查看
     * @param candidateSignUpVo
     * @return
     */
    public ExamResultVo examPaperView(CandidateSignUpVo candidateSignUpVo);

}
