package com.ruoyi.exam.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 题库管理表
 * </p>
 *
 * @author hougq
 * @since 2023-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("question_bank_manage")
@ApiModel(value="QuestionBankManage对象", description="题库管理表")
public class QuestionBankManage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "topic_id", type = IdType.AUTO)
    private String topicId;

    @ApiModelProperty(value = "题目序号")
    private String topicNum;

    @ApiModelProperty(value = "题目编号")
    private String topicCode;

    @ApiModelProperty(value = "题目内容")
    private String topicContent;

    @ApiModelProperty(value = "题目分类（1：法治思想 2：民法典 3：宪法 4：党内法规 5：行政处罚法）")
    private String topicSort;

    @ApiModelProperty(value = "题目类型（1：单选题 2：多选题 3：判断题）")
    private String topicType;

    @ApiModelProperty(value = "题目答案（1：正确 0：错误）")
    private String topicAnswer;

    @ApiModelProperty(value = "答案解析")
    private String answerAnalysis;

    @ApiModelProperty(value = "每题分数")
    private String perScore;

    @ApiModelProperty(value = "数据状态（1：正常  0：删除）")
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
     * 题目选项
     */
    public String topicOptions;

    private List<TopicOptions> topicOptionsList;


}
