package com.ruoyi.meap.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动触点统计情况对象 mads_acs
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
public class MadsAcs extends BaseEntity
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

    /** 触点 */
    @Excel(name = "触点")
    private String contactPoint;

    /** pv */
    @Excel(name = "pv")
    private Long pv;

    /** 参与用户数 */
    @Excel(name = "参与用户数")
    private Long uv;

    /** 参与活动用户累计（剔重） */
    @Excel(name = "参与活动用户累计", readConverterExp = "剔=重")
    private Long ljUv;

    /** 参与活动新增原生用户数 */
    @Excel(name = "参与活动新增原生用户数")
    private Long xzysUv;

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
    public void setContactPoint(String contactPoint) 
    {
        this.contactPoint = contactPoint;
    }

    public String getContactPoint() 
    {
        return contactPoint;
    }
    public void setPv(Long pv) 
    {
        this.pv = pv;
    }

    public Long getPv() 
    {
        return pv;
    }
    public void setUv(Long uv) 
    {
        this.uv = uv;
    }

    public Long getUv() 
    {
        return uv;
    }
    public void setLjUv(Long ljUv) 
    {
        this.ljUv = ljUv;
    }

    public Long getLjUv() 
    {
        return ljUv;
    }
    public void setXzysUv(Long xzysUv) 
    {
        this.xzysUv = xzysUv;
    }

    public Long getXzysUv() 
    {
        return xzysUv;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("abhsDate", getAbhsDate())
            .append("city", getCity())
            .append("channel", getChannel())
            .append("contactPoint", getContactPoint())
            .append("pv", getPv())
            .append("uv", getUv())
            .append("ljUv", getLjUv())
            .append("xzysUv", getXzysUv())
            .toString();
    }
}
