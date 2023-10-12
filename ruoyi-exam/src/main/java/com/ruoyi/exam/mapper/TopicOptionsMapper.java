package com.ruoyi.exam.mapper;

import com.ruoyi.exam.domain.TopicOptions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 题目选项字典表 Mapper 接口
 * </p>
 *
 * @author hougq
 * @since 2023-09-28
 */
public interface TopicOptionsMapper extends BaseMapper<TopicOptions> {

    /**
     * 新增题目选项
     * @param topicOptions
     * @return
     */
    public int insertTopicOptionsData(TopicOptions topicOptions);

    /**
     * 修改题目选项
     * @param topicOptions
     * @return
     */
    public int updateTopicOptions(TopicOptions topicOptions);

    /**
     * 根据题目ID获取对应选项
     * @param topicOptions
     * @return
     */
    public List<TopicOptions> selectTopicOptionsList(TopicOptions topicOptions);

}
