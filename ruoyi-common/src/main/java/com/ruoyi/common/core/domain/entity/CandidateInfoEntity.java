package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 考生信息导出列表
 * 
 * @author yxp
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CandidateInfoEntity
{
    private static final long serialVersionUID = 1L;

    /** 考生姓名 */
    @Excel(name = "考生姓名")
    private String candidateName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 人员类别 */
    @Excel(name = "人员类别")
    private String personCategory;

    /** 所属单位 */
    @Excel(name = "所属单位")
    private String unitName;

    /** 状态 */
    @Excel(name = "状态")
    private String personState;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("candidateName", getCandidateName())
                .append("mobile", getMobile())
                .append("personCategory", getPersonCategory())
                .append("unitName", getUnitName())
                .append("personState", getPersonState())
                .toString();
    }

}
