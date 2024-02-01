package com.ruoyi.meap.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 汇总对象 amdas_collect
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
public class AmdasCollect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 月份 */
    @Excel(name = "月份")
    private String month;

    /** 参与活动个数 */
    @Excel(name = "参与活动个数")
    private Long activityNum;

    /** 参与人数 */
    @Excel(name = "参与人数")
    private Long participantsNum;

    /** 和掌柜客户数 */
    @Excel(name = "和掌柜客户数")
    private Long hzgClientNum;

    /** 微厅客户数 */
    @Excel(name = "微厅客户数")
    private Long wtClientNum;

    /** wap客户数 */
    @Excel(name = "wap客户数")
    private Long wapClientNum;

    /** 手厅客户数 */
    @Excel(name = "手厅客户数")
    private Long stClientNum;

    /** 一个月 */
    @Excel(name = "一个月")
    private Long oneMonthValue;

    /** 占比 */
    @Excel(name = "占比")
    private String oneMonthRatio;

    /** 两个月 */
    @Excel(name = "两个月")
    private Long twoMonthValue;

    /** 占比 */
    @Excel(name = "占比")
    private String twoMonthRatio;

    /** 三个月 */
    @Excel(name = "三个月")
    private Long threeMonthValue;

    /** 占比 */
    @Excel(name = "占比")
    private String threeMonthRatio;

    /** 四个月 */
    @Excel(name = "四个月")
    private Long fourMonthValue;

    /** 占比 */
    @Excel(name = "占比")
    private String fourMonthRatio;

    /** 五个月 */
    @Excel(name = "五个月")
    private Long fiveMonthValue;

    /** 占比 */
    @Excel(name = "占比")
    private String fiveMonthRatio;

    /** 六个月 */
    @Excel(name = "六个月")
    private Long sixMonthValue;

    /** 占比 */
    @Excel(name = "占比")
    private String sixMonthRatio;

    /** 查充办客户数 */
    @Excel(name = "查充办客户数")
    private Long ccbClientNum;

    /** 查充办客户占比 */
    @Excel(name = "查充办客户占比")
    private String ccbClientRatio;

    /** 当月新增客户数 */
    @Excel(name = "当月新增客户数")
    private Long dyxzClientNum;

    /** 新增占比 */
    @Excel(name = "新增占比")
    private String dyxzClientRatio;

    /** 流量资源使用 */
    @Excel(name = "流量资源使用")
    private Long trafficResourceUsage;

    /** 话费资源使用 */
    @Excel(name = "话费资源使用")
    private Long phoneResourceUsage;

    /** 折让资源使用 */
    @Excel(name = "折让资源使用")
    private Long crResourceUsage;

    /** 其他资源使用 */
    @Excel(name = "其他资源使用")
    private Long otherResourceUsage;

    /** 有销量客户数 */
    @Excel(name = "有销量客户数")
    private Long yxlClientNum;

    /** 有销量客户占比 */
    @Excel(name = "有销量客户占比")
    private String yxlClientRatio;

    /** 参与活动用户业务总销量 */
    @Excel(name = "参与活动用户业务总销量")
    private Long cyhdyhywzxl;

    /** 销售贡献(笔/人) */
    @Excel(name = "销售贡献(笔/人)")
    private String salesContribution;

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
    public void setActivityNum(Long activityNum) 
    {
        this.activityNum = activityNum;
    }

    public Long getActivityNum() 
    {
        return activityNum;
    }
    public void setParticipantsNum(Long participantsNum) 
    {
        this.participantsNum = participantsNum;
    }

    public Long getParticipantsNum() 
    {
        return participantsNum;
    }
    public void setHzgClientNum(Long hzgClientNum) 
    {
        this.hzgClientNum = hzgClientNum;
    }

    public Long getHzgClientNum() 
    {
        return hzgClientNum;
    }
    public void setWtClientNum(Long wtClientNum) 
    {
        this.wtClientNum = wtClientNum;
    }

    public Long getWtClientNum() 
    {
        return wtClientNum;
    }
    public void setWapClientNum(Long wapClientNum) 
    {
        this.wapClientNum = wapClientNum;
    }

    public Long getWapClientNum() 
    {
        return wapClientNum;
    }
    public void setStClientNum(Long stClientNum) 
    {
        this.stClientNum = stClientNum;
    }

    public Long getStClientNum() 
    {
        return stClientNum;
    }
    public void setOneMonthValue(Long oneMonthValue) 
    {
        this.oneMonthValue = oneMonthValue;
    }

    public Long getOneMonthValue() 
    {
        return oneMonthValue;
    }
    public void setOneMonthRatio(String oneMonthRatio) 
    {
        this.oneMonthRatio = oneMonthRatio;
    }

    public String getOneMonthRatio() 
    {
        return oneMonthRatio;
    }
    public void setTwoMonthValue(Long twoMonthValue) 
    {
        this.twoMonthValue = twoMonthValue;
    }

    public Long getTwoMonthValue() 
    {
        return twoMonthValue;
    }
    public void setTwoMonthRatio(String twoMonthRatio) 
    {
        this.twoMonthRatio = twoMonthRatio;
    }

    public String getTwoMonthRatio() 
    {
        return twoMonthRatio;
    }
    public void setThreeMonthValue(Long threeMonthValue) 
    {
        this.threeMonthValue = threeMonthValue;
    }

    public Long getThreeMonthValue() 
    {
        return threeMonthValue;
    }
    public void setThreeMonthRatio(String threeMonthRatio) 
    {
        this.threeMonthRatio = threeMonthRatio;
    }

    public String getThreeMonthRatio() 
    {
        return threeMonthRatio;
    }
    public void setFourMonthValue(Long fourMonthValue) 
    {
        this.fourMonthValue = fourMonthValue;
    }

    public Long getFourMonthValue() 
    {
        return fourMonthValue;
    }
    public void setFourMonthRatio(String fourMonthRatio) 
    {
        this.fourMonthRatio = fourMonthRatio;
    }

    public String getFourMonthRatio() 
    {
        return fourMonthRatio;
    }
    public void setFiveMonthValue(Long fiveMonthValue) 
    {
        this.fiveMonthValue = fiveMonthValue;
    }

    public Long getFiveMonthValue() 
    {
        return fiveMonthValue;
    }
    public void setFiveMonthRatio(String fiveMonthRatio) 
    {
        this.fiveMonthRatio = fiveMonthRatio;
    }

    public String getFiveMonthRatio() 
    {
        return fiveMonthRatio;
    }
    public void setSixMonthValue(Long sixMonthValue) 
    {
        this.sixMonthValue = sixMonthValue;
    }

    public Long getSixMonthValue() 
    {
        return sixMonthValue;
    }
    public void setSixMonthRatio(String sixMonthRatio) 
    {
        this.sixMonthRatio = sixMonthRatio;
    }

    public String getSixMonthRatio() 
    {
        return sixMonthRatio;
    }
    public void setCcbClientNum(Long ccbClientNum) 
    {
        this.ccbClientNum = ccbClientNum;
    }

    public Long getCcbClientNum() 
    {
        return ccbClientNum;
    }
    public void setCcbClientRatio(String ccbClientRatio) 
    {
        this.ccbClientRatio = ccbClientRatio;
    }

    public String getCcbClientRatio() 
    {
        return ccbClientRatio;
    }
    public void setDyxzClientNum(Long dyxzClientNum) 
    {
        this.dyxzClientNum = dyxzClientNum;
    }

    public Long getDyxzClientNum() 
    {
        return dyxzClientNum;
    }
    public void setDyxzClientRatio(String dyxzClientRatio)
    {
        this.dyxzClientRatio = dyxzClientRatio;
    }

    public String getDyxzClientRatio()
    {
        return dyxzClientRatio;
    }
    public void setTrafficResourceUsage(Long trafficResourceUsage) 
    {
        this.trafficResourceUsage = trafficResourceUsage;
    }

    public Long getTrafficResourceUsage() 
    {
        return trafficResourceUsage;
    }
    public void setPhoneResourceUsage(Long phoneResourceUsage) 
    {
        this.phoneResourceUsage = phoneResourceUsage;
    }

    public Long getPhoneResourceUsage() 
    {
        return phoneResourceUsage;
    }
    public void setCrResourceUsage(Long crResourceUsage) 
    {
        this.crResourceUsage = crResourceUsage;
    }

    public Long getCrResourceUsage() 
    {
        return crResourceUsage;
    }
    public void setOtherResourceUsage(Long otherResourceUsage) 
    {
        this.otherResourceUsage = otherResourceUsage;
    }

    public Long getOtherResourceUsage() 
    {
        return otherResourceUsage;
    }
    public void setYxlClientNum(Long yxlClientNum) 
    {
        this.yxlClientNum = yxlClientNum;
    }

    public Long getYxlClientNum() 
    {
        return yxlClientNum;
    }
    public void setYxlClientRatio(String yxlClientRatio) 
    {
        this.yxlClientRatio = yxlClientRatio;
    }

    public String getYxlClientRatio() 
    {
        return yxlClientRatio;
    }
    public void setCyhdyhywzxl(Long cyhdyhywzxl) 
    {
        this.cyhdyhywzxl = cyhdyhywzxl;
    }

    public Long getCyhdyhywzxl() 
    {
        return cyhdyhywzxl;
    }
    public void setSalesContribution(String salesContribution) 
    {
        this.salesContribution = salesContribution;
    }

    public String getSalesContribution() 
    {
        return salesContribution;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("month", getMonth())
            .append("activityNum", getActivityNum())
            .append("participantsNum", getParticipantsNum())
            .append("hzgClientNum", getHzgClientNum())
            .append("wtClientNum", getWtClientNum())
            .append("wapClientNum", getWapClientNum())
            .append("stClientNum", getStClientNum())
            .append("oneMonthValue", getOneMonthValue())
            .append("oneMonthRatio", getOneMonthRatio())
            .append("twoMonthValue", getTwoMonthValue())
            .append("twoMonthRatio", getTwoMonthRatio())
            .append("threeMonthValue", getThreeMonthValue())
            .append("threeMonthRatio", getThreeMonthRatio())
            .append("fourMonthValue", getFourMonthValue())
            .append("fourMonthRatio", getFourMonthRatio())
            .append("fiveMonthValue", getFiveMonthValue())
            .append("fiveMonthRatio", getFiveMonthRatio())
            .append("sixMonthValue", getSixMonthValue())
            .append("sixMonthRatio", getSixMonthRatio())
            .append("ccbClientNum", getCcbClientNum())
            .append("ccbClientRatio", getCcbClientRatio())
            .append("dyxzClientNum", getDyxzClientNum())
            .append("dyxzClientRatio", getDyxzClientRatio())
            .append("trafficResourceUsage", getTrafficResourceUsage())
            .append("phoneResourceUsage", getPhoneResourceUsage())
            .append("crResourceUsage", getCrResourceUsage())
            .append("otherResourceUsage", getOtherResourceUsage())
            .append("yxlClientNum", getYxlClientNum())
            .append("yxlClientRatio", getYxlClientRatio())
            .append("cyhdyhywzxl", getCyhdyhywzxl())
            .append("salesContribution", getSalesContribution())
            .toString();
    }
}
