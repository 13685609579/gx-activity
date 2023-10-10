package com.ruoyi.exam.domain.vo;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 统计分析
 * </p>
 *
 * @author yxp
 * @since 2023-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StatisticalAnalysisVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 考试管理ID
     */
    private String examId;

    /**
     * 考试名称
     */
    private String examTitle;

    /**
     * 单位ID
     */
    private String unitId;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 考试年份
     */
    private String examYear;

    /**
     * 完成人数
     */
    private String completeCount;

    /**
     * 未完成人数
     */
    private String uncompletedCount;

    /**
     * 完成率
     */
    private String completeRate;

}
