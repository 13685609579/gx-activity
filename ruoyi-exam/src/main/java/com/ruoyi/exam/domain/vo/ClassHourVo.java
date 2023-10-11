package com.ruoyi.exam.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
/**
 * <p>
 * 封装获取题目分类、学时接口参数实体类
 * </p>
 *
 * @author yxp
 * @since 2023-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClassHourVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 考试ID
     */
    private String examId;

    /**
     * 人员类型（1：执法司法人员 2：非执法司法人员）
     */
    private String personType;

    /**
     * 考生ID
     */
    private String candidateId;

    /**
     * 考试学时年份
     */
    private String examYear;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;



}
