package com.ruoyi.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.entity.QuestionBank;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.exam.domain.QuestionBankManage;
import com.ruoyi.exam.domain.TopicOptions;
import com.ruoyi.exam.mapper.QuestionBankManageMapper;
import com.ruoyi.exam.mapper.TopicOptionsMapper;
import com.ruoyi.exam.service.QuestionBankManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.exam.util.DataUtils;
import com.ruoyi.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

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
                SysDictData sysDictData = sysDictDataMapper.selectDictDataById(Long.valueOf(m.getTopicSort()));
                m.setTopicSortName(sysDictData.getDictLabel());
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
        if(StringUtils.equals("1", questionBankManage.getTopicType()) || StringUtils.equals("2", questionBankManage.getTopicType())){
            questionBankManage.setPerScore("2");
        }
        if(StringUtils.equals("3", questionBankManage.getTopicType())){
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
        topicOptionsLambdaQueryWrapper.eq(TopicOptions::getTopicId, topicId)
                .eq(TopicOptions::getDelFlag, 0)
                .orderByDesc(TopicOptions::getOptionsState);
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

    /**
     * 删除题目
     * @param topicId
     * @return
     */
    @Override
    public int removeQuestionBank(String topicId) {
        int row = 1;
        if(StringUtils.isNotEmpty(topicId) && StringUtils.isNotBlank(topicId)){
            String[] topicIds = topicId.split(",");
            for(int i=0; i<topicIds.length; i++){
                QuestionBankManage questionBankManage = new QuestionBankManage();
                questionBankManage.setTopicId(topicIds[i]);
                questionBankManage.setDelFlag("1");
                row = questionBankManageMapper.updateQuestionBank(questionBankManage);
                if(1 == row){
                    LambdaQueryWrapper<TopicOptions> wrapper = new LambdaQueryWrapper<TopicOptions>();
                    wrapper.eq(TopicOptions::getDelFlag, 0)
                            .eq(TopicOptions::getTopicId, topicIds[i]);
                    List<TopicOptions> topicOptionsList = topicOptionsMapper.selectList(wrapper);
                    if(null != topicOptionsList && topicOptionsList.size()>0){
                        TopicOptions topicOptions = topicOptionsList.get(i);
                        topicOptions.setDelFlag("1");
                        topicOptionsMapper.updateById(topicOptions);
                    }
                }
                if(1 != row){
                    break;
                }
            }
        }
        return row;
    }

    /**
     * 导入题目数据
     *
     * @param questionBankList 题目数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    @Transactional
    public String importQuestionBank(List<QuestionBank> questionBankList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(questionBankList) || questionBankList.size() == 0)
        {
            throw new ServiceException("导入题目数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (QuestionBank questionBank : questionBankList) {
            try {
                QuestionBankManage questionBankManage = new QuestionBankManage();
                String topicId = DataUtils.uuids();
                questionBankManage.setTopicId(topicId);
                String maxTopicNum = questionBankManageMapper.selectMaxTopicNum();
                if(StringUtils.isNotEmpty(maxTopicNum) && StringUtils.isNotBlank(maxTopicNum)){
                    int maxTopicNumber = Integer.parseInt(maxTopicNum)+1;
                    questionBankManage.setTopicNum(String.valueOf(maxTopicNumber));
                }else{
                    questionBankManage.setTopicNum("1");
                }
                questionBankManage.setTopicCode(getTopicCode());
                questionBankManage.setTopicContent(questionBank.getTopicContent());
                questionBankManage.setTopicType(questionBank.getTopicType());
                questionBankManage.setTopicSort(questionBank.getTopicSort());
                if(StringUtils.equals("1", questionBank.getTopicType()) || StringUtils.equals("3", questionBank.getTopicType())){
                    questionBankManage.setPerScore("2");
                }
                if(StringUtils.equals("2", questionBank.getTopicType())){
                    questionBankManage.setPerScore("5");
                }
                String topicAnswer = questionBank.getTopicAnswer();
                String tAnswer = "";
                if(StringUtils.equals("1", questionBank.getTopicType()) || StringUtils.equals("2", questionBank.getTopicType())){
                    if(null != topicAnswer){
                        tAnswer = questionBank.getTopicAnswer();
                    }
                }
                if(StringUtils.equals("3", questionBank.getTopicType())){
                    if(null != topicAnswer){
                        if(topicAnswer.contains("选项一")){
                            if(questionBank.getOptionOne().contains("正确")){
                                tAnswer = "1";
                            }else{
                                tAnswer = "0";
                            }
                        }
                        if(topicAnswer.contains("选项二")){
                            if(questionBank.getOptionTwo().contains("正确")){
                                tAnswer = "1";
                            }else{
                                tAnswer = "0";
                            }
                        }
                    }
                }
                questionBankManage.setTopicAnswer(tAnswer);
                questionBankManage.setAnswerAnalysis(questionBank.getAnswerAnalysis());
                questionBankManage.setCreateBy(operName);
                int row = questionBankManageMapper.insertQuestionBankData(questionBankManage);
                List<TopicOptions> topicOptionsList = new ArrayList<>();
                if(StringUtils.equals("1", questionBank.getTopicType()) || StringUtils.equals("2", questionBank.getTopicType())){
                    TopicOptions topicOptions1 = new TopicOptions();
                    topicOptions1.setOptionsContent(questionBank.getOptionOne().replace(" ", ""));
                    TopicOptions topicOptions2 = new TopicOptions();
                    topicOptions2.setOptionsContent(questionBank.getOptionTwo().replace(" ", ""));
                    TopicOptions topicOptions3 = new TopicOptions();
                    topicOptions3.setOptionsContent(questionBank.getOptionThree().replace(" ", ""));
                    TopicOptions topicOptions4 = new TopicOptions();
                    topicOptions4.setOptionsContent(questionBank.getOptionFour().replace(" ", ""));
                    if(StringUtils.isNotBlank(topicAnswer) && StringUtils.isNotEmpty(topicAnswer)){
                        if(topicAnswer.contains("选项一")){
                            topicOptions1.setOptionsState("1");
                        }else{
                            topicOptions1.setOptionsState("0");
                        }
                        if(topicAnswer.contains("选项二")){
                            topicOptions2.setOptionsState("1");
                        }else{
                            topicOptions2.setOptionsState("0");
                        }
                        if(topicAnswer.contains("选项三")){
                            topicOptions3.setOptionsState("1");
                        }else{
                            topicOptions3.setOptionsState("0");
                        }
                        if(topicAnswer.contains("选项四")){
                            topicOptions4.setOptionsState("1");
                        }else{
                            topicOptions4.setOptionsState("0");
                        }
                    }
                    topicOptionsList.add(topicOptions1);
                    topicOptionsList.add(topicOptions2);
                    topicOptionsList.add(topicOptions3);
                    topicOptionsList.add(topicOptions4);
                }
                if(null != topicOptionsList && topicOptionsList.size()>0){
                    for(int i=0; i<topicOptionsList.size(); i++){
                        TopicOptions topicOptions = topicOptionsList.get(i);
                        topicOptions.setTopicOptionsId(DataUtils.uuids());
                        topicOptions.setTopicId(topicId);
                        topicOptions.setCreateBy(operName);
                        topicOptionsMapper.insertTopicOptionsData(topicOptions);
                    }
                }
                successNum++;
            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + "题目 " + questionBank.getTopicContent() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条");
        }
        return successMsg.toString();
    }

}
