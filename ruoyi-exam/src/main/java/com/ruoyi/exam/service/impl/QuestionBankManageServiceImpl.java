package com.ruoyi.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.QuestionBankManage;
import com.ruoyi.exam.domain.TopicOptions;
import com.ruoyi.exam.mapper.QuestionBankManageMapper;
import com.ruoyi.exam.mapper.TopicOptionsMapper;
import com.ruoyi.exam.service.QuestionBankManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.exam.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 题库管理表 服务实现类
 * </p>
 *
 * @author hougq
 * @since 2023-09-28
 */
@Service
public class QuestionBankManageServiceImpl extends ServiceImpl<QuestionBankManageMapper, QuestionBankManage> implements QuestionBankManageService {

    @Autowired
    private QuestionBankManageMapper questionBankManageMapper;

    @Autowired
    private TopicOptionsMapper topicOptionsMapper;

    /**
     * 获取题库列表
     * @param questionBankManage
     * @return
     */
    @Override
    public List<QuestionBankManage> selectQuestionBankList(QuestionBankManage questionBankManage) {
        List<QuestionBankManage> list = questionBankManageMapper.selectQuestionBankList(questionBankManage);
        return list;
    }

    /**
     * 新增题库题目
     * @param questionBankManage
     * @return
     */
    @Override
    public int insertQuestionBankData(QuestionBankManage questionBankManage) {
        String maxTopicNum = questionBankManageMapper.selectMaxTopicNum();
        if(StringUtils.isNotEmpty(maxTopicNum) && StringUtils.isNotBlank(maxTopicNum)){
            int maxTopicNumber = Integer.parseInt(maxTopicNum)+1;
            questionBankManage.setTopicNum(String.valueOf(maxTopicNumber));
        }else{
            questionBankManage.setTopicNum("1");
        }
        questionBankManage.setTopicId(DataUtils.uuids());
        int row = questionBankManageMapper.insertQuestionBankData(questionBankManage);
        List<TopicOptions> topicOptionsList = questionBankManage.getTopicOptionsList();
        if(null != topicOptionsList && topicOptionsList.size()>0){
            QuestionBankManage questionBankManage1 = questionBankManageMapper.selectNewQuestionBank();
            for(int i=0; i<topicOptionsList.size(); i++){
                TopicOptions topicOptions = topicOptionsList.get(i);
                topicOptions.setTopicOptionsId(DataUtils.uuids());
                topicOptions.setTopicId(questionBankManage1.getTopicId());
                topicOptions.setDelFlag("1");
                topicOptions.setCreateBy(questionBankManage.getCreateBy());
                topicOptionsMapper.insertTopicOptionsData(topicOptions);
            }
        }
        return row;
    }

    /**
     * 获取当前题目详情
     * @param topicId
     * @return
     */
    @Override
    public QuestionBankManage questionBankInfo(String topicId) {
        QuestionBankManage questionBankManage = questionBankManageMapper.questionBankInfo(topicId);
        LambdaQueryWrapper<TopicOptions> topicOptionsLambdaQueryWrapper = new LambdaQueryWrapper<TopicOptions>();
        topicOptionsLambdaQueryWrapper.eq(TopicOptions::getTopicId, topicId).orderByDesc(TopicOptions::getOptionsState);
        List<TopicOptions> topicOptionsList = topicOptionsMapper.selectList(topicOptionsLambdaQueryWrapper);
        questionBankManage.setTopicOptionsList(topicOptionsList);
        return questionBankManage;
    }

    /**
     * 修改当前题目
     * @param questionBankManage
     * @return
     */
    @Override
    public int updateQuestionBank(QuestionBankManage questionBankManage) {
        return 0;
    }
}
