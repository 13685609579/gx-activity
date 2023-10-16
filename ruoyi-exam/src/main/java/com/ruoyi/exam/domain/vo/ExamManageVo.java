package com.ruoyi.exam.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 首页重要信息
 * </p>
 *
 * @author yxp
 * @since 2023-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ExamManageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 考试ID
     */
    private String examId;

    /**
     * 考试标题
     */
    private String examTitle;

    /**
     * 考试状态（0：已结束 1：未结束）
     */
    private String paperState;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 考试年份
     */
    private String examYear;

    /**
     * 目标学时
     */
    private String targetHours;

    /**
     * 已获学时
     */
    private String acquiredHours;

}
