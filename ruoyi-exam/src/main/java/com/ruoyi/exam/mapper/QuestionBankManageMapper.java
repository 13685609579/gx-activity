package com.ruoyi.exam.mapper;

import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.exam.domain.QuestionBankManage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 题库管理表 Mapper 接口
 * </p>
 *
 * @author hougq
 * @since 2023-09-28
 */
public interface QuestionBankManageMapper extends BaseMapper<QuestionBankManage> {

    /**
     * 获取题库列表
     * @param questionBankManage
     * @return
     */
    public List<QuestionBankManage> selectQuestionBankList(QuestionBankManage questionBankManage);

    /**
     * 从题库中随机获取题目作为考题（单选题30道、多选题10道、判断题4道）
     * @param questionBankManage
     * @return
     */
    public List<QuestionBankManage> selectExamQuestion(QuestionBankManage questionBankManage);

    /**
     * 获取最新一条题目
     * @return
     */
    public QuestionBankManage selectNewQuestionBank();

    /**
     * 获取最新题目编号
     * @return
     */
    public String selectNewTopicCode(String today);
    /**
     * 获取题库题目最大题目编号
     * @return
     */
    public String selectMaxTopicNum();

    /**
     * 新增题库题目
     * @param questionBankManage
     * @return
     */
    public int insertQuestionBankData(QuestionBankManage questionBankManage);

    /**
     * 获取当前题目详情
     * @param topicId
     * @return
     */
    public QuestionBankManage questionBankInfo(String topicId);

    /**
     * 修改当前题目
     * @param questionBankManage
     * @return
     */
    public int updateQuestionBank(QuestionBankManage questionBankManage);

}
