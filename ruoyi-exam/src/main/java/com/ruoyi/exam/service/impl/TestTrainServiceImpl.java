package com.ruoyi.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.exam.domain.QuestionBankManage;
import com.ruoyi.exam.domain.TestTrain;
import com.ruoyi.exam.domain.TopicOptions;
import com.ruoyi.exam.domain.vo.TestTrainVo;
import com.ruoyi.exam.domain.vo.TopicSortEntityVo;
import com.ruoyi.exam.mapper.QuestionBankManageMapper;
import com.ruoyi.exam.mapper.TestTrainMapper;
import com.ruoyi.exam.mapper.TopicOptionsMapper;
import com.ruoyi.exam.service.TestTrainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 测试训练表 服务实现类
 * </p>
 *
 * @author yxp
 * @since 2023-10-13
 */
@Service
public class TestTrainServiceImpl extends ServiceImpl<TestTrainMapper, TestTrain> implements TestTrainService {

    @Autowired
    private TestTrainMapper testTrainMapper;

    @Autowired
    private TopicOptionsMapper topicOptionsMapper;

    @Autowired
    private QuestionBankManageMapper questionBankManageMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 测试训练
     * @param candidateId 考生ID
     * @param dictType 字典类型（题目分类参数值topic_sort）
     * @return
     */
    @Override
    public List<TopicSortEntityVo> topicSortList(String candidateId, String dictType) {
        SysDictData sysDictData = new SysDictData();
        sysDictData.setDictType(dictType);
        sysDictData.setStatus("0");
        List<SysDictData> sysDictDataList = sysDictDataMapper.selectDictDataList(sysDictData);
        List<TopicSortEntityVo> voList = new ArrayList<TopicSortEntityVo>();
        if(null != sysDictDataList && sysDictDataList.size()>0){
            for(int i=0; i<sysDictDataList.size(); i++){
                SysDictData data = sysDictDataList.get(i);
                TopicSortEntityVo entityVo = new TopicSortEntityVo();
                entityVo.setDictCode(data.getDictCode());
                entityVo.setDictSort(data.getDictSort());
                entityVo.setDictLabel(data.getDictLabel());
                entityVo.setDictValue(data.getDictValue());
                entityVo.setDictType(data.getDictType());
                TestTrain testTrain = new TestTrain();
                testTrain.setCandidateId(candidateId);
                testTrain.setTopicSort(String.valueOf(data.getDictCode()));
                TestTrainVo ttVo = null;
                Integer maxTopicNum = getMaxTopicNum(testTrain);
                if(null != maxTopicNum){
                    testTrain.setTopicNum(maxTopicNum);
                    ttVo = getLastTimeTestTopic(testTrain);
                }
                entityVo.setTrainVo(ttVo);
                voList.add(entityVo);
            }
        }
        return voList;
    }

    /**
     * 测试训练-继续上次
     * @param testTrain
     * @return
     */
    @Override
    public AjaxResult continueLastTime(TestTrain testTrain) {
        TestTrainVo ttVo = null;
        Integer maxTopicNum = getMaxTopicNum(testTrain);
        if(null != maxTopicNum){
            testTrain.setTopicNum(maxTopicNum);
            ttVo = getLastTimeTestTopic(testTrain);
        }
        AjaxResult ajaxResult = new AjaxResult(200, "获取当前题目成功！", ttVo);
        return ajaxResult;
    }

    /**
     * 测试-结束答题
     * @param testTrain
     * @return
     */
    @Override
    public int endAnswer(TestTrain testTrain) {
        TestTrain train = getTestTrain(testTrain);
        QuestionBankManage bankManage = questionBankManageMapper.questionBankInfo(testTrain.getTopicId());
        int row = 0;
        if(null != train){
            if(!StringUtils.equals(train.getTopicId(), testTrain.getTopicId())){
                row = insertTestTrain(testTrain, bankManage);
            }
        }else{
            row = insertTestTrain(testTrain, bankManage);
        }
        return row;
    }

    /**
     * 测试-提交
     * @param testTrain
     * @return
     */
    @Override
    public AjaxResult submitTest(TestTrain testTrain) {
        TestTrain train = getTestTrain(testTrain);
        QuestionBankManage bankManage = questionBankManageMapper.questionBankInfo(testTrain.getTopicId());
        if(null != train){
            setTestTrainAnswerResult(train, bankManage);
            if(!StringUtils.equals(train.getTopicId(), testTrain.getTopicId())){
                testTrain.setCandidateAnswer(testTrain.getCandidateAnswer());
                testTrain.setAnswerAnalysis(bankManage.getAnswerAnalysis());
                testTrain.setCreateBy(testTrain.getCreateBy());
                testTrain.setCreateTime(DateUtils.getNowDate());
                insertTestTrain(testTrain, bankManage);
            }else{
                train.setCandidateAnswer(testTrain.getCandidateAnswer());
                train.setAnswerAnalysis(bankManage.getAnswerAnalysis());
                train.setUpdateBy(testTrain.getUpdateBy());
                testTrainMapper.updateById(train);
            }
        }else{
            testTrain.setCandidateAnswer(testTrain.getCandidateAnswer());
            testTrain.setAnswerAnalysis(bankManage.getAnswerAnalysis());
            testTrain.setCreateBy(testTrain.getCreateBy());
            testTrain.setCreateTime(DateUtils.getNowDate());
            setTestTrainAnswerResult(testTrain, bankManage);
            insertTestTrain(testTrain, bankManage);
        }
        TestTrainVo ttVo = new TestTrainVo();
        Integer maxTopicNum = getMaxTopicNum(testTrain);
        if(null != maxTopicNum){
            testTrain.setTopicNum(maxTopicNum);
            ttVo = getLastTimeTestTopic(testTrain);
            ttVo.setTopicContent(bankManage.getTopicContent());
        }
        AjaxResult ajaxResult = new AjaxResult(200, "提交当前题目成功！", ttVo);
        return ajaxResult;
    }

    /**
     * 测试训练-重新开始
     * @param testTrain
     * @return
     */
    @Override
    public TestTrainVo restart(TestTrain testTrain) {
        QuestionBankManage bankManage = new QuestionBankManage();
        bankManage.setTopicSort(testTrain.getTopicSort());
        bankManage = questionBankManageMapper.getSortFirst(bankManage);
        TestTrainVo trainVo = getTestTrainVo(testTrain, bankManage);
        return trainVo;
    }

    /**
     * 测试训练-下一题
     * @param testTrain
     * @return
     */
    @Override
    public AjaxResult nextTestTopic(TestTrain testTrain) {
        AjaxResult ajaxResult = new AjaxResult();
        Integer code = 200;
        String msg = "";
        testTrain = getTestTrain(testTrain);
        QuestionBankManage bankManage = questionBankManageMapper.questionBankInfo(testTrain.getTopicId());
        bankManage = questionBankManageMapper.nextTestTopic(bankManage);
        TestTrainVo trainVo = new TestTrainVo();
        if(null != bankManage){
            trainVo = getTestTrainVo(testTrain, bankManage);
            msg = "获取下一题数据成功，请答题！";
        }else{
            code = 500;
            msg = "当前已是最后一题，无法继续答题！";
        }
        ajaxResult = new AjaxResult(code, msg, trainVo);
        return ajaxResult;
    }

    /**
     * 获取测试训练表当前考生、题目分类最新一题
     * @param testTrain
     * @return
     */
    public TestTrain getTestTrain(TestTrain testTrain){
        LambdaQueryWrapper<TestTrain> wrapper = new LambdaQueryWrapper<TestTrain>();
        wrapper.eq(TestTrain::getDelFlag, 0);
        if(StringUtils.isNotBlank(testTrain.getCandidateId()) && StringUtils.isNotEmpty(testTrain.getCandidateId())){
            wrapper.eq(TestTrain::getCandidateId, testTrain.getCandidateId());
        }
        if(StringUtils.isNotBlank(testTrain.getTopicSort()) && StringUtils.isNotEmpty(testTrain.getTopicSort())){
            wrapper.eq(TestTrain::getTopicSort, testTrain.getTopicSort());
        }
        wrapper.orderByDesc(TestTrain::getTopicNum)
                .last("limit 1");
        TestTrain train = testTrainMapper.selectOne(wrapper);
        return train;
    }

    /**
     * 新增测试训练表数据
     * @param testTrain
     * @param bankManage
     * @return
     */
    public int insertTestTrain(TestTrain testTrain, QuestionBankManage bankManage){
        int row = 0;
        testTrain.setTopicType(bankManage.getTopicType());
        testTrain.setDelFlag("0");
        testTrain.setCreateTime(DateUtils.getNowDate());
        Integer maxTopicNum = getMaxTopicNum(testTrain);
        if(null != maxTopicNum){
            testTrain.setTopicNum(maxTopicNum+1);
        }else{
            testTrain.setTopicNum(1);
        }
        row = testTrainMapper.insert(testTrain);
        return row;
    }

    /**
     * 设置答题结果是否正确（1：正确 0：错误）
     * @param testTrain
     * @param bankManage
     */
    public void setTestTrainAnswerResult(TestTrain testTrain, QuestionBankManage bankManage){
        if(null != bankManage && (StringUtils.equals("1", bankManage.getTopicType()) || StringUtils.equals("2", bankManage.getTopicType()))){
            String[] cAnswers = testTrain.getCandidateAnswer().split(",");
            if(cAnswers.length>0){
                for(int i=0; i<cAnswers.length; i++){
                    TopicOptions topicOptions = topicOptionsMapper.selectById(cAnswers[i]);
                    testTrain.setAnswerResult("1");
                    if(StringUtils.equals("0", topicOptions.getOptionsState())){
                        testTrain.setAnswerResult("0");
                        break;
                    }
                }
            }
        }
        if(null != bankManage && StringUtils.equals("3", bankManage.getTopicType())){
            if(StringUtils.equals(bankManage.getTopicAnswer(), testTrain.getCandidateAnswer())){
                testTrain.setAnswerResult("1");
            }else{
                testTrain.setAnswerResult("0");
            }
        }
    }

    /**
     * 获取当前考生当前题目分类测试题最大题目序号
     * @param testTrain
     * @return
     */
    public Integer getMaxTopicNum(TestTrain testTrain){
        TestTrain train = getTestTrain(testTrain);
        Integer maxTopicNum = null;
        if(null != train){
            maxTopicNum = train.getTopicNum();
        }
        return maxTopicNum;
    }

    /**
     *
     * @param testTrain
     * @return
     */
    public TestTrainVo getLastTimeTestTopic(TestTrain testTrain){
        TestTrain train = getTestTrain(testTrain);
        List<TopicOptions> topicOptionsList = new ArrayList<>();
        if(null != train && (StringUtils.equals("1", train.getTopicType()) || StringUtils.equals("2", train.getTopicType()))){
            TopicOptions topicOptions = new TopicOptions();
            topicOptions.setTopicId(train.getTopicId());
            topicOptions.setDelFlag("0");
            topicOptionsList = topicOptionsMapper.selectTopicOptionsList(topicOptions);
        }
        TestTrainVo ttVo = entityToVo(train);
        ttVo.setTopicOptionsList(topicOptionsList);
        return ttVo;
    }

    /**
     * TestTrain转为TestTrainVo
     * @param train
     * @return
     */
    public TestTrainVo entityToVo(TestTrain train){
        TestTrainVo ttVo = new TestTrainVo();
        ttVo.setId(train.getId());
        ttVo.setCandidateId(train.getCandidateId());
        ttVo.setTopicId(train.getTopicId());
        ttVo.setTopicSort(train.getTopicSort());
        ttVo.setTopicType(train.getTopicType());
        ttVo.setTopicNum(train.getTopicNum());
        ttVo.setCandidateAnswer(train.getCandidateAnswer());
        ttVo.setAnswerResult(train.getAnswerResult());
        ttVo.setAnswerAnalysis(train.getAnswerAnalysis());
        ttVo.setDelFlag(train.getDelFlag());
        ttVo.setCreateTime(train.getCreateTime());
        ttVo.setUpdateTime(train.getUpdateTime());
        ttVo.setCreateBy(train.getCreateBy());
        ttVo.setUpdateBy(train.getUpdateBy());
        ttVo.setRemark(train.getRemark());
        return ttVo;
    }

    /**
     * 将题库管理表数据封装到TestTrainVo中
     * @param testTrain
     * @param bankManage
     * @return
     */
    public TestTrainVo getTestTrainVo(TestTrain testTrain, QuestionBankManage bankManage){
        TestTrainVo trainVo = new TestTrainVo();
        trainVo.setCandidateId(testTrain.getCandidateId());
        trainVo.setTopicId(bankManage.getTopicId());
        trainVo.setTopicContent(bankManage.getTopicContent());
        trainVo.setTopicSort(bankManage.getTopicSort());
        trainVo.setTopicType(bankManage.getTopicType());
        trainVo.setTopicNum(Integer.parseInt(bankManage.getTopicNum()));
        TopicOptions topicOptions = new TopicOptions();
        topicOptions.setTopicId(bankManage.getTopicId());
        List<TopicOptions> topicOptionsList = topicOptionsMapper.selectTopicOptionsList(topicOptions);
        trainVo.setTopicOptionsList(topicOptionsList);
        return trainVo;
    }

}
