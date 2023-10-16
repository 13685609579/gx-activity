package com.ruoyi.exam.domain.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.exam.domain.TopicOptions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 测试训练表
 * </p>
 *
 * @author yxp
 * @since 2023-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TestTrainVo implements Serializable {

    private static final long serialVersionUID = 1L;

    //主键ID
    private Long id;

    //考生ID
    private String candidateId;

    //题库ID
    private String topicId;

    //题目内容
    private String topicContent;

    //题目分类（数据字典编码）
    private String topicSort;

    //题目类型（1：单选题 2：多选题 3：判断题）
    private String topicType;

    //试卷题目序号
    private Integer topicNum;

    //考生答案
    private String candidateAnswer;

    //答题结果是否正确（1：正确 0：错误）
    private String answerResult;

    //答案解析
    private String answerAnalysis;

    //数据状态（1：正常  0：删除）
    private String delFlag;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //创建人
    private String createBy;

    //更新人
    private String updateBy;

    //备注
    private String remark;

    //题目选项
    private List<TopicOptions> topicOptionsList;


}
