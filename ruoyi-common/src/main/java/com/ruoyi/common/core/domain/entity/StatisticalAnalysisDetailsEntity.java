package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <p>
 * 统计分析详情导出
 * </p>
 *
 * @author yxp
 * @since 2023-10-10
 */
public class StatisticalAnalysisDetailsEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 姓名 */
    @Excel(name = "姓名")
    private String candidateName;

    /** 所属单位 */
    @Excel(name = "所属单位")
    private String unitName;

    /** 账号 */
    @Excel(name = "账号")
    private String mobile;

    /** 已完成学时 */
    @Excel(name = "已完成学时")
    private String acquiredHours;

    @Excel(name = "考试年份")
    private String examYear;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAcquiredHours() {
        return acquiredHours;
    }

    public void setAcquiredHours(String acquiredHours) {
        this.acquiredHours = acquiredHours;
    }

    public String getExamYear() {
        return examYear;
    }

    public void setExamYear(String examYear) {
        this.examYear = examYear;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("candidateName", getCandidateName())
                .append("unitName", getUnitName())
                .append("mobile", getMobile())
                .append("acquiredHours", getAcquiredHours())
                .append("examYear", getExamYear())
                .toString();
    }

}
