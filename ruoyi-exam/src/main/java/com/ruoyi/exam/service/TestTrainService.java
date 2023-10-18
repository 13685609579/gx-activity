package com.ruoyi.exam.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.exam.domain.TestTrain;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.exam.domain.vo.TestTrainVo;
import com.ruoyi.exam.domain.vo.TopicSortEntityVo;

import java.util.List;

/**
 * <p>
 * 测试训练表 服务类
 * </p>
 *
 * @author yxp
 * @since 2023-10-13
 */
public interface TestTrainService extends IService<TestTrain> {

    /**
     * 测试训练
     * @param candidateId 考生ID
     * @param dictType 字典类型（题目分类参数值topic_sort）
     * @return
     */
    public List<TopicSortEntityVo> topicSortList(String candidateId, String dictType);

    /**
     * 测试训练-继续上次
     * @param testTrain
     * @return
     */
    public AjaxResult continueLastTime(TestTrain testTrain);

    /**
     * 测试-结束答题
     * @param testTrain
     * @return
     */
    public int endAnswer(TestTrain testTrain);

    /**
    /**
     * 测试-提交
     * @param testTrain
     * @return
     */
    public AjaxResult submitTest(TestTrain testTrain);

    /**
     * 测试训练-重新开始
     * @param testTrain
     * @return
     */
    public TestTrainVo restart(TestTrain testTrain);

    /**
     * 测试训练-下一题
     * @param testTrain
     * @return
     */
    public AjaxResult nextTestTopic(TestTrain testTrain);

}
