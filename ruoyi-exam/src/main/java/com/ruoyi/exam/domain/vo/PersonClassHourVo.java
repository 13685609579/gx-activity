package com.ruoyi.exam.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 司法人员学时
 * </p>
 *
 * @author yxp
 * @since 2023-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PersonClassHourVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学时ID
     */
    private String hourId;

    /**
     * 人员类型
     */
    private String personType;

    /**
     * 题目分类名称
     */
    private String dictLabel;

    /**
     * 学时
     */
    private String targetHours;



}
