package com.ruoyi.exam.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 考生信息表
 * </p>
 *
 * @author hougq
 * @since 2023-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("candidate_info")
@ApiModel(value="CandidateInfo对象", description="考生信息表")
public class CandidateInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "candidate_id", type = IdType.AUTO)
    private String candidateId;

    @ApiModelProperty(value = "考生姓名")
    private String candidateName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "人员类别（1：执法司法人员 2：非执法司法人员）")
    private String personCategory;

    @ApiModelProperty(value = "所属单位")
    private String unitId;
    private String unitName;

    @ApiModelProperty(value = "状态（2：待审核 1：通过 0：不通过）")
    private String personState;

    @ApiModelProperty(value = "数据状态（0：正常  1：删除）")
    private String delFlag;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新人")
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "备注")
    private String remark;

    private String rowNum;

    private String openId;

}
