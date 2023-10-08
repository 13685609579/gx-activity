package com.ruoyi.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.utils.DateUtils;
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

import java.util.Collections;
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
        if(null != list && list.size()>0){
            list.stream().forEach(m->{
                TopicOptions topicOptions = new TopicOptions();
                topicOptions.setTopicId(m.getTopicId());
                List<TopicOptions> topicOptionsList = topicOptionsMapper.selectTopicOptionsList(topicOptions);
                if(null != topicOptionsList && topicOptionsList.size()>0){
                    String topicOptions1 = "";
                    for(int i=0; i<topicOptionsList.size(); i++){
                        TopicOptions options = topicOptionsList.get(i);
                        if(i<topicOptionsList.size()-1){
                            topicOptions1 += options.getOptionsContent()+"、";
                        }else{
                            topicOptions1 += options.getOptionsContent();
                        }
                    }
                    m.setTopicOptions(topicOptions1);
                }
            });
        }
        return list;
    }

    /**
     * 获取最新题目编号
     * @return
     */
    @Override
    public String getTopicCode() {
        String newTopicCode = questionBankManageMapper.selectNewTopicCode(DateUtils.getDate()+" 00:00:00");
        String topicCode = "";
        if(StringUtils.isEmpty(newTopicCode) || StringUtils.isBlank(newTopicCode)){
            topicCode = "SFKS"+ DateUtils.getDate().replace("-", "")+"1";
        }else{
            int serial_number = Integer.parseInt(newTopicCode.substring(12));
            topicCode = "SFKS"+ DateUtils.getDate().replace("-", "")+(serial_number+1);
        }
        return topicCode;
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
        String topicId = DataUtils.uuids();
        questionBankManage.setTopicId(topicId);
        if(StringUtils.equals("1", questionBankManage.getTopicType()) || StringUtils.equals("3", questionBankManage.getTopicType())){
            questionBankManage.setPerScore("2");
        }
        if(StringUtils.equals("2", questionBankManage.getTopicType())){
            questionBankManage.setPerScore("5");
        }
        int row = questionBankManageMapper.insertQuestionBankData(questionBankManage);
        List<TopicOptions> topicOptionsList = questionBankManage.getTopicOptionsList();
        if(null != topicOptionsList && topicOptionsList.size()>0){
            for(int i=0; i<topicOptionsList.size(); i++){
                TopicOptions topicOptions = topicOptionsList.get(i);
                topicOptions.setTopicOptionsId(DataUtils.uuids());
                topicOptions.setTopicId(topicId);
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
        int row = questionBankManageMapper.updateQuestionBank(questionBankManage);
        if(StringUtils.equals("1", questionBankManage.getTopicType()) || StringUtils.equals("3", questionBankManage.getTopicType())){
            List<TopicOptions> topicOptionsList = questionBankManage.getTopicOptionsList();
            if(null != topicOptionsList && topicOptionsList.size()>0){
                topicOptionsList.stream().forEach(m->{
                    topicOptionsMapper.updateTopicOptions(m);
                });
            }
        }
        return row;
    }
}
