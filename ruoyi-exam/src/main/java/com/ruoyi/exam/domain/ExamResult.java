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
 * 考试结果表
 * </p>
 *
 * @author hougq
 * @since 2023-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("exam_result")
@ApiModel(value="ExamResult对象", description="考试结果表")
public class ExamResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "exam_result_id", type = IdType.AUTO)
    private String examResultId;

    @ApiModelProperty(value = "考生ID")
    private String candidateId;

    @ApiModelProperty(value = "考题ID")
    private String topicId;

    @ApiModelProperty(value = "题目分类（1：法治思想 2：民法典 3：宪法 4：党内法规 5：行政处罚法）")
    private String topicSort;

    @ApiModelProperty(value = "题目类型（1：单选题 2：多选题 3：判断题）")
    private String topicType;

    @ApiModelProperty(value = "考生答案")
    private String candidateAnswer;

    @ApiModelProperty(value = "答题结果是否正确（1：正确 0：错误）")
    private String answerResult;

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
