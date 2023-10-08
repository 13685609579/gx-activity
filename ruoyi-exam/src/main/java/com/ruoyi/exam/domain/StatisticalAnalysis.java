package com.ruoyi.exam.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
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
 * @since 2023-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StatisticalAnalysis extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "人员类别（1：执法司法人员 2：非执法司法人员）")
    private String personCategory;

    @ApiModelProperty(value = "单位ID")
    private String unitId;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "完成学时")
    private String classHour;

    @ApiModelProperty(value = "考试年份")
    private String examYear;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    /**
     * 考试阶段
     */
    private String exam_phase;

}
