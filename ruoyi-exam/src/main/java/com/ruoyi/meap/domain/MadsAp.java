package com.ruoyi.meap.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动参与情况对象 mads_ap
 * 
 * @author youxiuping
 * @date 2024-01-30
 */
public class MadsAp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 地市 */
    @Excel(name = "地市")
    private String city;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date hdcyDate;

    /** 渠道 */
    @Excel(name = "渠道")
    private String channel;

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

    /** 占比 */
    @Excel(name = "占比")
    private String xzysRatio;

    /** 参与活动新增月活用户数 */
    @Excel(name = "参与活动新增月活用户数")
    private Long xzyhUv;

    /** 占比 */
    @Excel(name = "占比")
    private String xzyhRatio;

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

    /** 有业务办理行为客户数 */
    @Excel(name = "有业务办理行为客户数")
    private Long yywblxwVv;

    /** 占比 */
    @Excel(name = "占比")
    private String yywblxwRatio;

    /** 业务办理成功客户数 */
    @Excel(name = "业务办理成功客户数")
    private Long ywblcgVv;

    /** 占比 */
    @Excel(name = "占比")
    private String ywblcgRatio;

    /** 分享用户数 */
    @Excel(name = "分享用户数")
    private Long fxUv;

    /** 占比 */
    @Excel(name = "占比")
    private String fxRatio;

    /** 受邀未参与活动用户数 */
    @Excel(name = "受邀未参与活动用户数")
    private Long sywcyhdUv;

    /** 占比 */
    @Excel(name = "占比")
    private String sywcyhdRatio;

    /** 任务一参与客户数 */
    @Excel(name = "任务一参与客户数")
    private Long rwycyVv;

    /** 任务一完成客户数 */
    @Excel(name = "任务一完成客户数")
    private Long rwywcVv;

    /** 任务二参与客户数 */
    @Excel(name = "任务二参与客户数")
    private Long rwecyVv;

    /** 任务二完成客户数 */
    @Excel(name = "任务二完成客户数")
    private Long rwewcVv;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setHdcyDate(Date hdcyDate) 
    {
        this.hdcyDate = hdcyDate;
    }

    public Date getHdcyDate() 
    {
        return hdcyDate;
    }
    public void setChannel(String channel) 
    {
        this.channel = channel;
    }

    public String getChannel() 
    {
        return channel;
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
    public void setXzysRatio(String xzysRatio) 
    {
        this.xzysRatio = xzysRatio;
    }

    public String getXzysRatio() 
    {
        return xzysRatio;
    }
    public void setXzyhUv(Long xzyhUv) 
    {
        this.xzyhUv = xzyhUv;
    }

    public Long getXzyhUv() 
    {
        return xzyhUv;
    }
    public void setXzyhRatio(String xzyhRatio) 
    {
        this.xzyhRatio = xzyhRatio;
    }

    public String getXzyhRatio() 
    {
        return xzyhRatio;
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
    public void setYywblxwVv(Long yywblxwVv) 
    {
        this.yywblxwVv = yywblxwVv;
    }

    public Long getYywblxwVv() 
    {
        return yywblxwVv;
    }
    public void setYywblxwRatio(String yywblxwRatio) 
    {
        this.yywblxwRatio = yywblxwRatio;
    }

    public String getYywblxwRatio() 
    {
        return yywblxwRatio;
    }
    public void setYwblcgVv(Long ywblcgVv) 
    {
        this.ywblcgVv = ywblcgVv;
    }

    public Long getYwblcgVv() 
    {
        return ywblcgVv;
    }
    public void setYwblcgRatio(String ywblcgRatio) 
    {
        this.ywblcgRatio = ywblcgRatio;
    }

    public String getYwblcgRatio() 
    {
        return ywblcgRatio;
    }
    public void setFxUv(Long fxUv) 
    {
        this.fxUv = fxUv;
    }

    public Long getFxUv() 
    {
        return fxUv;
    }
    public void setFxRatio(String fxRatio) 
    {
        this.fxRatio = fxRatio;
    }

    public String getFxRatio() 
    {
        return fxRatio;
    }
    public void setSywcyhdUv(Long sywcyhdUv) 
    {
        this.sywcyhdUv = sywcyhdUv;
    }

    public Long getSywcyhdUv() 
    {
        return sywcyhdUv;
    }
    public void setSywcyhdRatio(String sywcyhdRatio) 
    {
        this.sywcyhdRatio = sywcyhdRatio;
    }

    public String getSywcyhdRatio() 
    {
        return sywcyhdRatio;
    }
    public void setRwycyVv(Long rwycyVv) 
    {
        this.rwycyVv = rwycyVv;
    }

    public Long getRwycyVv() 
    {
        return rwycyVv;
    }
    public void setRwywcVv(Long rwywcVv) 
    {
        this.rwywcVv = rwywcVv;
    }

    public Long getRwywcVv() 
    {
        return rwywcVv;
    }
    public void setRwecyVv(Long rwecyVv) 
    {
        this.rwecyVv = rwecyVv;
    }

    public Long getRwecyVv() 
    {
        return rwecyVv;
    }
    public void setRwewcVv(Long rwewcVv) 
    {
        this.rwewcVv = rwewcVv;
    }

    public Long getRwewcVv() 
    {
        return rwewcVv;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("city", getCity())
            .append("hdcyDate", getHdcyDate())
            .append("channel", getChannel())
            .append("pv", getPv())
            .append("uv", getUv())
            .append("ljUv", getLjUv())
            .append("xzysUv", getXzysUv())
            .append("xzysRatio", getXzysRatio())
            .append("xzyhUv", getXzyhUv())
            .append("xzyhRatio", getXzyhRatio())
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
            .append("yywblxwVv", getYywblxwVv())
            .append("yywblxwRatio", getYywblxwRatio())
            .append("ywblcgVv", getYwblcgVv())
            .append("ywblcgRatio", getYwblcgRatio())
            .append("fxUv", getFxUv())
            .append("fxRatio", getFxRatio())
            .append("sywcyhdUv", getSywcyhdUv())
            .append("sywcyhdRatio", getSywcyhdRatio())
            .append("rwycyVv", getRwycyVv())
            .append("rwywcVv", getRwywcVv())
            .append("rwecyVv", getRwecyVv())
            .append("rwewcVv", getRwewcVv())
            .toString();
    }
}
