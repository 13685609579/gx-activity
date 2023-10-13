package com.ruoyi.exam.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 答题卡
 * </p>
 *
 * @author yxp
 * @since 2023-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AnswerSheetVo {

    //题目类型（1：单选题 2：多选题 3：判断题）
    private String topicType;

    //题目序号
    private Integer topicNum;

    //题目总条数
    private Integer topicTotal;

    //题目内容（题目标题）
    private String topicContent;

    //题目选项
    private List<TopicOptions> topicOptionsList;

    //考生答案
    private String candidateAnswer;

    //正确答案
    private String correctAnswer;

    //答案解析
    private String answerAnalysis;

    //答题结果（1：正确 0：错误 -1：未答）")
    private String answerResult;

}
