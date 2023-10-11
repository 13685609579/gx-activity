package com.ruoyi.exam.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 考生报名表
 * </p>
 *
 * @author hougq
 * @since 2023-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("candidate_sign_up")
@ApiModel(value="CandidateSignUp对象", description="考生报名表")
public class CandidateSignUp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "sign_up_id", type = IdType.AUTO)
    private String signUpId;

    @ApiModelProperty(value = "考生ID")
    private String candidateId;

    @ApiModelProperty(value = "考生姓名")
    private String candidateName;

    @ApiModelProperty(value = "人员类别（1：执法司法人员 2：非执法司法人员）")
    private String personCategory;

    @ApiModelProperty(value = "所属单位")
    private String unitId;

    @ApiModelProperty(value = "考试管理ID")
    private String examId;

    @ApiModelProperty(value = "数据状态（1：正常  0：删除）")
    private String delFlag;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;


}
