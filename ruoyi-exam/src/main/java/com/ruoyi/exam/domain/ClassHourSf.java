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
 * 司法人员学时管理表
 * </p>
 *
 * @author hougq
 * @since 2023-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("class_hour_sf")
@ApiModel(value="ClassHourSf对象", description="司法人员学时管理表")
public class ClassHourSf implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "hour_id", type = IdType.AUTO)
    private String hourId;

    @ApiModelProperty(value = "考试管理ID")
    private String examId;

    @ApiModelProperty(value = "字典编码")
    private Long dictCode;

    @ApiModelProperty(value = "目标学时")
    private String targetHours;

    @ApiModelProperty(value = "人员类型（1：执法司法人员 2：非执法司法人员）")
    private String personType;

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

    /**
     * 题目分类名称
     */
    private String dictLabel;

}
