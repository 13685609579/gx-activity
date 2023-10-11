package com.ruoyi.exam.domain.vo;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 统计分析详情
 * </p>
 *
 * @author yxp
 * @since 2023-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StatisticalAnalysisDetailsVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位ID")
    private String unitId;

    @ApiModelProperty(value = "考生姓名")
    private String candidateName;

    @ApiModelProperty(value = "账号（手机号）")
    private String mobile;

    @ApiModelProperty(value = "考试年份")
    private String examYear;

    @ApiModelProperty(value = "主键ID")
    private String candidateId;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "考试管理ID")
    private String examId;

    @ApiModelProperty(value = "考试标题")
    private String examTitle;

    /**
     * 已完成学时
     */
    private String acquiredHours;

}
