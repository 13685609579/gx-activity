package com.ruoyi.exam.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
/**
 * <p>
 * 封装信息填写确定接口参数实体类
 * </p>
 *
 * @author yxp
 * @since 2023-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CandidateSignUpVo implements Serializable {

    private static final long serialVersionUID = 1L;

    //考生ID
    private String candidateId;

    //考生姓名
    private String candidateName;

    //所属单位
    private String unitId;

    //考试管理ID
    private String examId;

    //题目分类
    private String topicSort;

    //题目ID
    private String topicId;

    //创建人
    private String createBy;

    //考试剩余时间
    private String remainTime;

    //考生答卷ID
    private Long paperStateId;

    //修改人
    private String updateBy;

    //试卷题目序号
    private Integer topicNum;

    //考生答案
    private String candidateAnswer;

    //试卷题目总条数
    private int topicTotal;

    //查看答题卡来源（1：提交试卷 2：答题卡按钮）
    private int source;

}
