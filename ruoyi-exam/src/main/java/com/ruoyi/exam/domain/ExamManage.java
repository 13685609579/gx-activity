package com.ruoyi.exam.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.exam.domain.vo.CreditHoursDistributionVo;
import com.ruoyi.exam.domain.vo.PersonClassHourVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 考试管理表
 * </p>
 *
 * @author hougq
 * @since 2023-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("exam_manage")
@ApiModel(value="ExamManage对象", description="考试管理表")
public class ExamManage extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "exam_id", type = IdType.AUTO)
    private String examId;

    @ApiModelProperty(value = "考试标题")
    private String examTitle;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "考试年份")
    private String examYear;

    @ApiModelProperty(value = "完成获取学时执法司法人员")
    private String classHourZf;

    @ApiModelProperty(value = "完成获取学时非执法司法人员")
    private String classHourFzf;

    @ApiModelProperty(value = "是否发布（1：是 0：否）")
    private String publishState;

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

    /**
     * 司法人员完成获取学时
     */
    private Map<String, List<PersonClassHourVo>> sfClassHours;

    List<ClassHourSf> classHourSfList;




}
