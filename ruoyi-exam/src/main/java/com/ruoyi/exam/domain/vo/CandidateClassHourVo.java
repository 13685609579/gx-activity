package com.ruoyi.exam.domain.vo;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 考生当前考试已完成学时实体类
 * </p>
 *
 * @author yxp
 * @since 2023-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CandidateClassHourVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考试管理ID")
    private String examId;

    @ApiModelProperty(value = "单位ID")
    private String unitId;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "考试年份")
    private String examYear;

    @ApiModelProperty(value = "考生")
    private String candidateId;

    @ApiModelProperty(value = "完成学时")
    private String classHour;

    /**
     * 完成状态（1：完成 0：未完成）
     */
    private String completeState;

    private List<String> unitIds;

}
