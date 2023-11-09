package com.ruoyi.exam.domain.vo;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 考生考试记录
 * </p>
 *
 * @author yxp
 * @since 2023-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CandidateExamRecordVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //考生ID
    private String candidateId;

    //考生姓名
    private String candidateName;

    //所属单位
    private String unitName;

    //考试标题
    private String examTitle;

    //题目分类
    private String topicSort;

    //考试年份
    private String examYear;

    //开始时间
    private String startTime;

    //结束时间
    private String endTime;

    //目标学时
    private String targetHours;

    //已获学时
    private String acquiredHours;

    private List<String> unitIds;

}
