package com.ruoyi.meap.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动参与分析对象 amdas_apa
 * 
 * @author youxiuping
 * @date 2024-01-31
 */
public class AmdasApa extends BaseEntity
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

    /** 参与人数 */
    @Excel(name = "参与人数")
    private Long participantsNum;

    /** 总资源（元） */
    @Excel(name = "总资源", readConverterExp = "元=")
    private Long totalResource;

    /** 已兑换资源（元） */
    @Excel(name = "已兑换资源", readConverterExp = "元=")
    private Long convertedResource;

    /** 人均使用资源（元） */
    @Excel(name = "人均使用资源", readConverterExp = "元=")
    private String perCapitaUseResource;

    /** 和掌柜资源（话费：元） */
    @Excel(name = "和掌柜资源", readConverterExp = "话=费：元")
    private String shopkeeperResourceBill;

    /** 和掌柜资源（流量：GB） */
    @Excel(name = "和掌柜资源", readConverterExp = "流=量：GB")
    private String shopkeeperResourceFlowRate;

    /** 和掌柜资源（折让：元） */
    @Excel(name = "和掌柜资源", readConverterExp = "折=让：元")
    private String shopkeeperResourceAllowance;

    /** 微厅资源（话费：元） */
    @Excel(name = "微厅资源", readConverterExp = "话=费：元")
    private String wtResourceBill;

    /** 微厅资源（流量：GB） */
    @Excel(name = "微厅资源", readConverterExp = "流=量：GB")
    private String wtResourceFlowRate;

    /** 微厅资源（折让：元） */
    @Excel(name = "微厅资源", readConverterExp = "折=让：元")
    private String wtResourceAllowance;

    /** 手厅资源（话费：元） */
    @Excel(name = "手厅资源", readConverterExp = "话=费：元")
    private String stResourceBill;

    /** 手厅资源（流量：元） */
    @Excel(name = "手厅资源", readConverterExp = "流=量：元")
    private String stResourceFlowRate;

    /** 手厅资源（折让：元） */
    @Excel(name = "手厅资源", readConverterExp = "折=让：元")
    private String stResourceAllowance;

    /** 仅当月活跃 */
    @Excel(name = "仅当月活跃")
    private Long monthActive;

    /** 占比 */
    @Excel(name = "占比")
    private String monthRatio;

    /** 连续活跃2月 */
    @Excel(name = "连续活跃2月")
    private Long towMonthActive;

    /** 占比 */
    @Excel(name = "占比")
    private String towMonthRatio;

    /** 连续活跃3月 */
    @Excel(name = "连续活跃3月")
    private Long threeMonthActive;

    /** 占比 */
    @Excel(name = "占比")
    private String threeMonthRatio;

    /** 连续活跃4月 */
    @Excel(name = "连续活跃4月")
    private Long fourMonthActive;

    /** 占比 */
    @Excel(name = "占比")
    private String fourMonthRatio;

    /** 连续活跃5月 */
    @Excel(name = "连续活跃5月")
    private Long fiveMonthActive;

    /** 占比 */
    @Excel(name = "占比")
    private String fiveMonthRatio;

    /** 连续活跃6月 */
    @Excel(name = "连续活跃6月")
    private Long sixMonthActive;

    /** 占比 */
    @Excel(name = "占比")
    private String sixMonthRatio;

    /** 查充办客户数 */
    @Excel(name = "查充办客户数")
    private Long checkCustomersNum;

    /** 占比 */
    @Excel(name = "占比")
    private String checkCustomersRatio;

    /** 当月新增客户数 */
    @Excel(name = "当月新增客户数")
    private Long monthNewcustomersNum;

    /** 占比 */
    @Excel(name = "占比")
    private String monthNewcustomersRatio;

    /** 有销量客户数 */
    @Excel(name = "有销量客户数")
    private Long salesCustomersNum;

    /** 占比 */
    @Excel(name = "占比")
    private String salesCustomersRatio;

    /** 业务总销量 */
    @Excel(name = "业务总销量")
    private Long totalBusinessSales;

    /** 人均销量 */
    @Excel(name = "人均销量")
    private String salesPerCapita;

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
    public void setParticipantsNum(Long participantsNum) 
    {
        this.participantsNum = participantsNum;
    }

    public Long getParticipantsNum() 
    {
        return participantsNum;
    }
    public void setTotalResource(Long totalResource) 
    {
        this.totalResource = totalResource;
    }

    public Long getTotalResource() 
    {
        return totalResource;
    }
    public void setConvertedResource(Long convertedResource) 
    {
        this.convertedResource = convertedResource;
    }

    public Long getConvertedResource() 
    {
        return convertedResource;
    }
    public void setPerCapitaUseResource(String perCapitaUseResource) 
    {
        this.perCapitaUseResource = perCapitaUseResource;
    }

    public String getPerCapitaUseResource() 
    {
        return perCapitaUseResource;
    }
    public void setShopkeeperResourceBill(String shopkeeperResourceBill) 
    {
        this.shopkeeperResourceBill = shopkeeperResourceBill;
    }

    public String getShopkeeperResourceBill() 
    {
        return shopkeeperResourceBill;
    }
    public void setShopkeeperResourceFlowRate(String shopkeeperResourceFlowRate) 
    {
        this.shopkeeperResourceFlowRate = shopkeeperResourceFlowRate;
    }

    public String getShopkeeperResourceFlowRate() 
    {
        return shopkeeperResourceFlowRate;
    }
    public void setShopkeeperResourceAllowance(String shopkeeperResourceAllowance) 
    {
        this.shopkeeperResourceAllowance = shopkeeperResourceAllowance;
    }

    public String getShopkeeperResourceAllowance() 
    {
        return shopkeeperResourceAllowance;
    }
    public void setWtResourceBill(String wtResourceBill) 
    {
        this.wtResourceBill = wtResourceBill;
    }

    public String getWtResourceBill() 
    {
        return wtResourceBill;
    }
    public void setWtResourceFlowRate(String wtResourceFlowRate) 
    {
        this.wtResourceFlowRate = wtResourceFlowRate;
    }

    public String getWtResourceFlowRate() 
    {
        return wtResourceFlowRate;
    }
    public void setWtResourceAllowance(String wtResourceAllowance) 
    {
        this.wtResourceAllowance = wtResourceAllowance;
    }

    public String getWtResourceAllowance() 
    {
        return wtResourceAllowance;
    }
    public void setStResourceBill(String stResourceBill) 
    {
        this.stResourceBill = stResourceBill;
    }

    public String getStResourceBill() 
    {
        return stResourceBill;
    }
    public void setStResourceFlowRate(String stResourceFlowRate) 
    {
        this.stResourceFlowRate = stResourceFlowRate;
    }

    public String getStResourceFlowRate() 
    {
        return stResourceFlowRate;
    }
    public void setStResourceAllowance(String stResourceAllowance) 
    {
        this.stResourceAllowance = stResourceAllowance;
    }

    public String getStResourceAllowance() 
    {
        return stResourceAllowance;
    }
    public void setMonthActive(Long monthActive) 
    {
        this.monthActive = monthActive;
    }

    public Long getMonthActive() 
    {
        return monthActive;
    }
    public void setMonthRatio(String monthRatio) 
    {
        this.monthRatio = monthRatio;
    }

    public String getMonthRatio() 
    {
        return monthRatio;
    }
    public void setTowMonthActive(Long towMonthActive) 
    {
        this.towMonthActive = towMonthActive;
    }

    public Long getTowMonthActive() 
    {
        return towMonthActive;
    }
    public void setTowMonthRatio(String towMonthRatio) 
    {
        this.towMonthRatio = towMonthRatio;
    }

    public String getTowMonthRatio() 
    {
        return towMonthRatio;
    }
    public void setThreeMonthActive(Long threeMonthActive) 
    {
        this.threeMonthActive = threeMonthActive;
    }

    public Long getThreeMonthActive() 
    {
        return threeMonthActive;
    }
    public void setThreeMonthRatio(String threeMonthRatio) 
    {
        this.threeMonthRatio = threeMonthRatio;
    }

    public String getThreeMonthRatio() 
    {
        return threeMonthRatio;
    }
    public void setFourMonthActive(Long fourMonthActive) 
    {
        this.fourMonthActive = fourMonthActive;
    }

    public Long getFourMonthActive() 
    {
        return fourMonthActive;
    }
    public void setFourMonthRatio(String fourMonthRatio) 
    {
        this.fourMonthRatio = fourMonthRatio;
    }

    public String getFourMonthRatio() 
    {
        return fourMonthRatio;
    }
    public void setFiveMonthActive(Long fiveMonthActive) 
    {
        this.fiveMonthActive = fiveMonthActive;
    }

    public Long getFiveMonthActive() 
    {
        return fiveMonthActive;
    }
    public void setFiveMonthRatio(String fiveMonthRatio) 
    {
        this.fiveMonthRatio = fiveMonthRatio;
    }

    public String getFiveMonthRatio() 
    {
        return fiveMonthRatio;
    }
    public void setSixMonthActive(Long sixMonthActive) 
    {
        this.sixMonthActive = sixMonthActive;
    }

    public Long getSixMonthActive() 
    {
        return sixMonthActive;
    }
    public void setSixMonthRatio(String sixMonthRatio) 
    {
        this.sixMonthRatio = sixMonthRatio;
    }

    public String getSixMonthRatio() 
    {
        return sixMonthRatio;
    }
    public void setCheckCustomersNum(Long checkCustomersNum) 
    {
        this.checkCustomersNum = checkCustomersNum;
    }

    public Long getCheckCustomersNum() 
    {
        return checkCustomersNum;
    }
    public void setCheckCustomersRatio(String checkCustomersRatio) 
    {
        this.checkCustomersRatio = checkCustomersRatio;
    }

    public String getCheckCustomersRatio() 
    {
        return checkCustomersRatio;
    }
    public void setMonthNewcustomersNum(Long monthNewcustomersNum) 
    {
        this.monthNewcustomersNum = monthNewcustomersNum;
    }

    public Long getMonthNewcustomersNum() 
    {
        return monthNewcustomersNum;
    }
    public void setMonthNewcustomersRatio(String monthNewcustomersRatio) 
    {
        this.monthNewcustomersRatio = monthNewcustomersRatio;
    }

    public String getMonthNewcustomersRatio() 
    {
        return monthNewcustomersRatio;
    }
    public void setSalesCustomersNum(Long salesCustomersNum) 
    {
        this.salesCustomersNum = salesCustomersNum;
    }

    public Long getSalesCustomersNum() 
    {
        return salesCustomersNum;
    }
    public void setSalesCustomersRatio(String salesCustomersRatio) 
    {
        this.salesCustomersRatio = salesCustomersRatio;
    }

    public String getSalesCustomersRatio() 
    {
        return salesCustomersRatio;
    }
    public void setTotalBusinessSales(Long totalBusinessSales) 
    {
        this.totalBusinessSales = totalBusinessSales;
    }

    public Long getTotalBusinessSales() 
    {
        return totalBusinessSales;
    }
    public void setSalesPerCapita(String salesPerCapita) 
    {
        this.salesPerCapita = salesPerCapita;
    }

    public String getSalesPerCapita() 
    {
        return salesPerCapita;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("month", getMonth())
            .append("activityName", getActivityName())
            .append("participantsNum", getParticipantsNum())
            .append("totalResource", getTotalResource())
            .append("convertedResource", getConvertedResource())
            .append("perCapitaUseResource", getPerCapitaUseResource())
            .append("shopkeeperResourceBill", getShopkeeperResourceBill())
            .append("shopkeeperResourceFlowRate", getShopkeeperResourceFlowRate())
            .append("shopkeeperResourceAllowance", getShopkeeperResourceAllowance())
            .append("wtResourceBill", getWtResourceBill())
            .append("wtResourceFlowRate", getWtResourceFlowRate())
            .append("wtResourceAllowance", getWtResourceAllowance())
            .append("stResourceBill", getStResourceBill())
            .append("stResourceFlowRate", getStResourceFlowRate())
            .append("stResourceAllowance", getStResourceAllowance())
            .append("monthActive", getMonthActive())
            .append("monthRatio", getMonthRatio())
            .append("towMonthActive", getTowMonthActive())
            .append("towMonthRatio", getTowMonthRatio())
            .append("threeMonthActive", getThreeMonthActive())
            .append("threeMonthRatio", getThreeMonthRatio())
            .append("fourMonthActive", getFourMonthActive())
            .append("fourMonthRatio", getFourMonthRatio())
            .append("fiveMonthActive", getFiveMonthActive())
            .append("fiveMonthRatio", getFiveMonthRatio())
            .append("sixMonthActive", getSixMonthActive())
            .append("sixMonthRatio", getSixMonthRatio())
            .append("checkCustomersNum", getCheckCustomersNum())
            .append("checkCustomersRatio", getCheckCustomersRatio())
            .append("monthNewcustomersNum", getMonthNewcustomersNum())
            .append("monthNewcustomersRatio", getMonthNewcustomersRatio())
            .append("salesCustomersNum", getSalesCustomersNum())
            .append("salesCustomersRatio", getSalesCustomersRatio())
            .append("totalBusinessSales", getTotalBusinessSales())
            .append("salesPerCapita", getSalesPerCapita())
            .toString();
    }
}
