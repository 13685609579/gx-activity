package com.ruoyi.exam.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 题目题型、分数、题数
 * </p>
 *
 * @author yxp
 * @since 2023-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TopicTypeEntityVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 题目题型
     */
    private String topicType;

    /**
     * 每题分数
     */
    private String perScore;

    /**
     * 题数
     */
    private String topicCount;

}
