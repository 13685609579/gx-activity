package com.ruoyi.exam.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
/**
 * <p>
 * 题目分类、学时
 * </p>
 *
 * @author yxp
 * @since 2023-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictLabelClassHourVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 题目分类名称
     */
    private String dictLabel;

    /**
     * 目标学时
     */
    private String targetHours;

    /**
     * 已获学时
     */
    private String classHour;

}
