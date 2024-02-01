package com.ruoyi.meap.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动奖品发放情况对象 mads_epds
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
public class MadsEpds extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date epdsDate;

    /** 地市 */
    @Excel(name = "地市")
    private String city;

    /** 渠道 */
    @Excel(name = "渠道")
    private String channel;

    /** 活动参与用户数 */
    @Excel(name = "活动参与用户数")
    private Long hdcyUv;

    /** 流量券发放量 */
    @Excel(name = "流量券发放量")
    private Long llqffl;

    /** 流量券使用量 */
    @Excel(name = "流量券使用量")
    private Long llqsyl;

    /** 兑换率 */
    @Excel(name = "兑换率")
    private String llqDhRate;

    /** 话费券发放量 */
    @Excel(name = "话费券发放量")
    private Long hfqffl;

    /** 话费券使用量 */
    @Excel(name = "话费券使用量")
    private Long hfqsyl;

    /** 兑换率 */
    @Excel(name = "兑换率")
    private String hfqDhRate;

    /** 业务券发放量 */
    @Excel(name = "业务券发放量")
    private Long ywqffl;

    /** 业务券使用量 */
    @Excel(name = "业务券使用量")
    private Long ywqsyl;

    /** 兑换率 */
    @Excel(name = "兑换率")
    private String ywqDhRate;

    /** 充值券发放量 */
    @Excel(name = "充值券发放量")
    private Long czqffl;

    /** 充值券使用量 */
    @Excel(name = "充值券使用量")
    private Long czqsyl;

    /** 兑换率 */
    @Excel(name = "兑换率")
    private String czqDhRate;

    /** 获奖用户数 */
    @Excel(name = "获奖用户数")
    private Long hjUv;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEpdsDate(Date epdsDate) 
    {
        this.epdsDate = epdsDate;
    }

    public Date getEpdsDate() 
    {
        return epdsDate;
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
    public void setHdcyUv(Long hdcyUv) 
    {
        this.hdcyUv = hdcyUv;
    }

    public Long getHdcyUv() 
    {
        return hdcyUv;
    }
    public void setLlqffl(Long llqffl) 
    {
        this.llqffl = llqffl;
    }

    public Long getLlqffl() 
    {
        return llqffl;
    }
    public void setLlqsyl(Long llqsyl) 
    {
        this.llqsyl = llqsyl;
    }

    public Long getLlqsyl() 
    {
        return llqsyl;
    }
    public void setLlqDhRate(String llqDhRate) 
    {
        this.llqDhRate = llqDhRate;
    }

    public String getLlqDhRate() 
    {
        return llqDhRate;
    }
    public void setHfqffl(Long hfqffl) 
    {
        this.hfqffl = hfqffl;
    }

    public Long getHfqffl() 
    {
        return hfqffl;
    }
    public void setHfqsyl(Long hfqsyl) 
    {
        this.hfqsyl = hfqsyl;
    }

    public Long getHfqsyl() 
    {
        return hfqsyl;
    }
    public void setHfqDhRate(String hfqDhRate) 
    {
        this.hfqDhRate = hfqDhRate;
    }

    public String getHfqDhRate() 
    {
        return hfqDhRate;
    }
    public void setYwqffl(Long ywqffl) 
    {
        this.ywqffl = ywqffl;
    }

    public Long getYwqffl() 
    {
        return ywqffl;
    }
    public void setYwqsyl(Long ywqsyl) 
    {
        this.ywqsyl = ywqsyl;
    }

    public Long getYwqsyl() 
    {
        return ywqsyl;
    }
    public void setYwqDhRate(String ywqDhRate) 
    {
        this.ywqDhRate = ywqDhRate;
    }

    public String getYwqDhRate() 
    {
        return ywqDhRate;
    }
    public void setCzqffl(Long czqffl) 
    {
        this.czqffl = czqffl;
    }

    public Long getCzqffl() 
    {
        return czqffl;
    }
    public void setCzqsyl(Long czqsyl) 
    {
        this.czqsyl = czqsyl;
    }

    public Long getCzqsyl() 
    {
        return czqsyl;
    }
    public void setCzqDhRate(String czqDhRate) 
    {
        this.czqDhRate = czqDhRate;
    }

    public String getCzqDhRate() 
    {
        return czqDhRate;
    }
    public void setHjUv(Long hjUv) 
    {
        this.hjUv = hjUv;
    }

    public Long getHjUv() 
    {
        return hjUv;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("epdsDate", getEpdsDate())
            .append("city", getCity())
            .append("channel", getChannel())
            .append("hdcyUv", getHdcyUv())
            .append("llqffl", getLlqffl())
            .append("llqsyl", getLlqsyl())
            .append("llqDhRate", getLlqDhRate())
            .append("hfqffl", getHfqffl())
            .append("hfqsyl", getHfqsyl())
            .append("hfqDhRate", getHfqDhRate())
            .append("ywqffl", getYwqffl())
            .append("ywqsyl", getYwqsyl())
            .append("ywqDhRate", getYwqDhRate())
            .append("czqffl", getCzqffl())
            .append("czqsyl", getCzqsyl())
            .append("czqDhRate", getCzqDhRate())
            .append("hjUv", getHjUv())
            .toString();
    }
}
