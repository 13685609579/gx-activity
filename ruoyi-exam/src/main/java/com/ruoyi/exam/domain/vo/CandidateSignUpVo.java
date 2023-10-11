package com.ruoyi.exam.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
/**
 * <p>
 * 封装信息填写确定接口参数实体类
 * </p>
 *
 * @author yxp
 * @since 2023-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CandidateSignUpVo implements Serializable {

    private static final long serialVersionUID = 1L;

    //考生ID
    private String candidateId;

    //考生姓名
    private String candidateName;

    //所属单位
    private String unitId;

    //考试管理ID
    private String examId;

    //题目分类
    private String topicSort;

    //创建人
    private String createBy;

}
