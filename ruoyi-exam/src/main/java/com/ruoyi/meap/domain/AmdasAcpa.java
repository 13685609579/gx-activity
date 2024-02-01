package com.ruoyi.meap.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动交叉参与分析对象 amdas_acpa
 * 
 * @author ruoyi
 * @date 2024-01-31
 */
public class AmdasAcpa extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 月份 */
    @Excel(name = "月份")
    private String month;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String activityName;

    /** 期刊 */
    @Excel(name = "期刊")
    private String periodical;

    /** 数额 */
    @Excel(name = "数额")
    private Long amount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMonth(String month) 
    {
        this.month = month;
    }

    public String getMonth() 
    {
        return month;
    }
    public void setActivityName(String activityName) 
    {
        this.activityName = activityName;
    }

    public String getActivityName() 
    {
        return activityName;
    }
    public void setPeriodical(String periodical) 
    {
        this.periodical = periodical;
    }

    public String getPeriodical() 
    {
        return periodical;
    }
    public void setAmount(Long amount) 
    {
        this.amount = amount;
    }

    public Long getAmount() 
    {
        return amount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("month", getMonth())
            .append("activityName", getActivityName())
            .append("periodical", getPeriodical())
            .append("amount", getAmount())
            .toString();
    }
}
