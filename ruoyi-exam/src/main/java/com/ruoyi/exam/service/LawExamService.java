package com.ruoyi.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.exam.domain.ExamManage;
import com.ruoyi.exam.domain.vo.LawExamVo;

import java.util.List;

/**
 * <p>
 * 学法考试 服务类
 * </p>
 *
 * @author yxp
 * @since 2023-10-09
 */
public interface LawExamService extends IService<LawExamVo> {

    /**
     * 考试（结题考试）
     * @param openId
     * @return
     */
    public LawExamVo getFinalExam(String openId);

}
