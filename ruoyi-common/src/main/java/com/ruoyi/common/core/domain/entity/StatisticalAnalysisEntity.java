package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 统计分析导出列表
 * 
 * @author yxp
 */
public class StatisticalAnalysisEntity
{
    private static final long serialVersionUID = 1L;

    /** 所属单位 */
    @Excel(name = "所属单位")
    private String unitName;

    /** 完成人数 */
    @Excel(name = "完成人数")
    private String completeCount;

    /** 完成率 */
    @Excel(name = "完成率")
    private String completeRate;

    /** 未完成人数 */
    @Excel(name = "未完成人数")
    private String uncompletedCount;

    /** 考试年份 */
    @Excel(name = "考试年份")
    private String examYear;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getCompleteCount() {
        return completeCount;
    }

    public void setCompleteCount(String completeCount) {
        this.completeCount = completeCount;
    }

    public String getCompleteRate() {
        return completeRate;
    }

    public void setCompleteRate(String completeRate) {
        this.completeRate = completeRate;
    }

    public String getUncompletedCount() {
        return uncompletedCount;
    }

    public void setUncompletedCount(String uncompletedCount) {
        this.uncompletedCount = uncompletedCount;
    }

    public String getExamYear() {
        return examYear;
    }

    public void setExamYear(String examYear) {
        this.examYear = examYear;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("unitName", getUnitName())
                .append("completeCount", getCompleteCount())
                .append("completeRate", getCompleteRate())
                .append("uncompletedCount", getUncompletedCount())
                .append("examYear", getExamYear())
                .toString();
    }

}
