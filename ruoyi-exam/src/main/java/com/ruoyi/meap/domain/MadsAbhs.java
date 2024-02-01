package com.ruoyi.meap.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动业务办理情况对象 mads_abhs
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
public class MadsAbhs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date abhsDate;

    /** 地市 */
    @Excel(name = "地市")
    private String city;

    /** 渠道 */
    @Excel(name = "渠道")
    private String channel;

    /** 活动参与量 */
    @Excel(name = "活动参与量")
    private Long hdcyl;

    /** 业务点击量 */
    @Excel(name = "业务点击量")
    private Long clkValue;

    /** 业务办理量 */
    @Excel(name = "业务办理量")
    private Long handleValue;

    /** 业务办理成功量 */
    @Excel(name = "业务办理成功量")
    private Long handleSuccessValue;

    /** 业务占当日线上销量占比 */
    @Excel(name = "业务占当日线上销量占比")
    private String bussnessSaleRatio;

    /** 参与活动用户业务办理量 */
    @Excel(name = "参与活动用户业务办理量")
    private Long participateHandleValue;

    /** 中奖客户数 */
    @Excel(name = "中奖客户数")
    private Long prizeUv;

    /** 获奖用户业务办理成功量 */
    @Excel(name = "获奖用户业务办理成功量")
    private Long prizeHandleSuccess;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAbhsDate(Date abhsDate) 
    {
        this.abhsDate = abhsDate;
    }

    public Date getAbhsDate() 
    {
        return abhsDate;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setChannel(String channel) 
    {
        this.channel = channel;
    }

    public String getChannel() 
    {
        return channel;
    }
    public void setHdcyl(Long hdcyl) 
    {
        this.hdcyl = hdcyl;
    }

    public Long getHdcyl() 
    {
        return hdcyl;
    }
    public void setClkValue(Long clkValue) 
    {
        this.clkValue = clkValue;
    }

    public Long getClkValue() 
    {
        return clkValue;
    }
    public void setHandleValue(Long handleValue) 
    {
        this.handleValue = handleValue;
    }

    public Long getHandleValue() 
    {
        return handleValue;
    }
    public void setHandleSuccessValue(Long handleSuccessValue) 
    {
        this.handleSuccessValue = handleSuccessValue;
    }

    public Long getHandleSuccessValue() 
    {
        return handleSuccessValue;
    }
    public void setBussnessSaleRatio(String bussnessSaleRatio) 
    {
        this.bussnessSaleRatio = bussnessSaleRatio;
    }

    public String getBussnessSaleRatio() 
    {
        return bussnessSaleRatio;
    }
    public void setParticipateHandleValue(Long participateHandleValue) 
    {
        this.participateHandleValue = participateHandleValue;
    }

    public Long getParticipateHandleValue() 
    {
        return participateHandleValue;
    }
    public void setPrizeUv(Long prizeUv) 
    {
        this.prizeUv = prizeUv;
    }

    public Long getPrizeUv() 
    {
        return prizeUv;
    }
    public void setPrizeHandleSuccess(Long prizeHandleSuccess) 
    {
        this.prizeHandleSuccess = prizeHandleSuccess;
    }

    public Long getPrizeHandleSuccess() 
    {
        return prizeHandleSuccess;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("abhsDate", getAbhsDate())
            .append("city", getCity())
            .append("channel", getChannel())
            .append("hdcyl", getHdcyl())
            .append("clkValue", getClkValue())
            .append("handleValue", getHandleValue())
            .append("handleSuccessValue", getHandleSuccessValue())
            .append("bussnessSaleRatio", getBussnessSaleRatio())
            .append("participateHandleValue", getParticipateHandleValue())
            .append("prizeUv", getPrizeUv())
            .append("prizeHandleSuccess", getPrizeHandleSuccess())
            .toString();
    }
}
