package com.ruoyi.exam.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 司法考试-考生试卷状态表
 * </p>
 *
 * @author yxp
 * @since 2023-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("candidate_paper_state")
@ApiModel(value="CandidatePaperState对象", description="司法考试-考生试卷状态表")
public class CandidatePaperState implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "考生ID")
    private String candidateId;

    @ApiModelProperty(value = "考试管理ID")
    private String examId;

    @ApiModelProperty(value = "考试试卷状态（0：已结束 1：未结束）")
    private String paperState;

    @ApiModelProperty(value = "考试开始时间")
    private String startTime;

    @ApiModelProperty(value = "考试结束时间")
    private String endTime;

    @ApiModelProperty(value = "当前考试剩余时间")
    private String remainTime;

    @ApiModelProperty(value = "数据状态（0：正常 1：删除）")
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


}
