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
 * 司法考试-考生考试结果表
 * </p>
 *
 * @author yxp
 * @since 2023-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("person_class_hour")
@ApiModel(value="PersonClassHour对象", description="司法考试-考生考试结果表")
public class PersonClassHour implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "class_hour_id", type = IdType.AUTO)
    private Long classHourId;

    @ApiModelProperty(value = "考生ID")
    private String candidateId;

    @ApiModelProperty(value = "本次作答分数")
    private String thisScore;

    @ApiModelProperty(value = "正确数")
    private String correctNum;

    @ApiModelProperty(value = "错误数")
    private String errorNum;

    @ApiModelProperty(value = "学时")
    private String classHour;

    @ApiModelProperty(value = "考试ID")
    private String examId;

    @ApiModelProperty(value = "题目分类（数据字典编码）")
    private String topicSort;

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


}
